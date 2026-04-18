package com.platform.testing.event.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.platform.testing.domain.constant.RunStatus;
import java.time.Instant;

/**
 * Engine → Report (via Kafka).
 * "This test case has finished (PASSED/FAILED/ERROR)."
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestCaseCompletedEvent(
        String runId,
        String testCaseId,
        String testCaseName,
        String executionTargetId,
        RunStatus status,
        long durationMs,
        int totalSteps,
        int passedSteps,
        int failedSteps,
        String errorMessage,
        String stackTrace,
        Instant completedAt
) {
    public TestCaseCompletedEvent {
        if (completedAt == null) {
            completedAt = Instant.now();
        }
    }
}