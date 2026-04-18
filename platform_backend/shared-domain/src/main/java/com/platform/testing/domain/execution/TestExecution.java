package com.platform.testing.domain.execution;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate Root — tracks the full lifecycle of one test execution.
 * Lives in memory during a run; state is published via Kafka and cached in Redis.
 */
public class TestExecution {

    private final String executionId;
    private final String testCaseId;
    private final String testCaseName;
    private final String testType;
    private final String environmentId;

    private ExecutionStatus status;
    private Instant startedAt;
    private Instant finishedAt;
    private int currentStepIndex;
    private final List<StepExecutionResult> stepResults;
    private String errorMessage;

    public TestExecution(String testCaseId, String testCaseName, String testType, String environmentId) {
        this.executionId = UUID.randomUUID().toString();
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.testType = testType;
        this.environmentId = environmentId;
        this.status = ExecutionStatus.PENDING;
        this.currentStepIndex = 0;
        this.stepResults = new ArrayList<>();
    }

    // ---- Domain Behaviour ----

    public void start() {
        if (status != ExecutionStatus.PENDING) {
            throw new IllegalStateException("Cannot start execution in status: " + status);
        }
        this.status = ExecutionStatus.RUNNING;
        this.startedAt = Instant.now();
    }

    public void recordStepResult(StepExecutionResult result) {
        if (status != ExecutionStatus.RUNNING) {
            throw new IllegalStateException("Cannot record step in status: " + status);
        }
        stepResults.add(result);
        currentStepIndex++;

        if (result.status() == StepStatus.FAILED) {
            this.status = ExecutionStatus.FAILED;
            this.finishedAt = Instant.now();
            this.errorMessage = result.errorMessage();
        }
    }

    public void complete() {
        if (status == ExecutionStatus.RUNNING) {
            this.status = ExecutionStatus.PASSED;
            this.finishedAt = Instant.now();
        }
    }

    public void abort(String reason) {
        this.status = ExecutionStatus.ABORTED;
        this.finishedAt = Instant.now();
        this.errorMessage = reason;
    }

    public long durationMillis() {
        if (startedAt == null) return 0;
        var end = finishedAt != null ? finishedAt : Instant.now();
        return end.toEpochMilli() - startedAt.toEpochMilli();
    }

    public double progressPercent(int totalSteps) {
        if (totalSteps == 0) return 0.0;
        return (double) currentStepIndex / totalSteps * 100.0;
    }

    // ---- JSON serialization ----

    public JsonObject toJson() {
        var stepsArray = new JsonArray();
        stepResults.forEach(r -> stepsArray.add(r.toJson()));

        return new JsonObject()
                .put("executionId", executionId)
                .put("testCaseId", testCaseId)
                .put("testCaseName", testCaseName)
                .put("testType", testType)
                .put("environmentId", environmentId)
                .put("status", status.name())
                .put("currentStepIndex", currentStepIndex)
                .put("durationMillis", durationMillis())
                .put("errorMessage", errorMessage)
                .put("startedAt", startedAt != null ? startedAt.toString() : null)
                .put("finishedAt", finishedAt != null ? finishedAt.toString() : null)
                .put("stepResults", stepsArray);
    }

    // ---- Getters ----

    public String getExecutionId() { return executionId; }
    public String getTestCaseId() { return testCaseId; }
    public String getTestCaseName() { return testCaseName; }
    public String getTestType() { return testType; }
    public String getEnvironmentId() { return environmentId; }
    public ExecutionStatus getStatus() { return status; }
    public Instant getStartedAt() { return startedAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public int getCurrentStepIndex() { return currentStepIndex; }
    public List<StepExecutionResult> getStepResults() {
        return Collections.unmodifiableList(stepResults);
    }
    public String getErrorMessage() { return errorMessage; }
}
