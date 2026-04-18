package com.platform.testing.domain.execution;

import com.platform.testing.domain.common.DomainEntity;
import com.platform.testing.domain.constant.RunStatus;
import com.platform.testing.domain.testcase.TestCaseId;
import java.time.Instant;
import java.util.*;

public class TestCaseResult implements DomainEntity {

    private final String id;
    private final TestCaseId testCaseId;
    private final ExecutionTargetId executionTargetId;
    private RunStatus status;
    private final List<TestStepResult> stepResults;
    private Instant startedAt;
    private Instant completedAt;
    private long durationMs;
    private String errorMessage;

    public static TestCaseResult create(TestCaseId testCaseId, ExecutionTargetId executionTargetId) {
        return new TestCaseResult(UUID.randomUUID().toString(), testCaseId, executionTargetId);
    }

    public static TestCaseResult reconstitute(String id, TestCaseId testCaseId,
                                              ExecutionTargetId executionTargetId, RunStatus status,
                                              List<TestStepResult> stepResults, Instant startedAt,
                                              Instant completedAt, long durationMs, String errorMessage) {
        TestCaseResult r = new TestCaseResult(id, testCaseId, executionTargetId);
        r.status = status;
        r.stepResults.addAll(stepResults);
        r.startedAt = startedAt;
        r.completedAt = completedAt;
        r.durationMs = durationMs;
        r.errorMessage = errorMessage;
        return r;
    }

    private TestCaseResult(String id, TestCaseId testCaseId, ExecutionTargetId executionTargetId) {
        this.id = id;
        this.testCaseId = testCaseId;
        this.executionTargetId = executionTargetId;
        this.status = RunStatus.QUEUED;
        this.stepResults = new ArrayList<>();
        this.startedAt = Instant.now();
    }

    public void addStepResult(TestStepResult result) {
        this.stepResults.add(result);
    }

    public void complete(RunStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.completedAt = Instant.now();
        this.durationMs = completedAt.toEpochMilli() - startedAt.toEpochMilli();
    }

    public String getId() { return id; }
    public TestCaseId getTestCaseId() { return testCaseId; }
    public ExecutionTargetId getExecutionTargetId() { return executionTargetId; }
    public RunStatus getStatus() { return status; }
    public List<TestStepResult> getStepResults() { return Collections.unmodifiableList(stepResults); }
    public Instant getStartedAt() { return startedAt; }
    public Instant getCompletedAt() { return completedAt; }
    public long getDurationMs() { return durationMs; }
    public String getErrorMessage() { return errorMessage; }
}
