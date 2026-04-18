package com.platform.testing.event.execution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.platform.testing.domain.constant.RunStatus;
import java.time.Instant;

/**
 * Engine → Report (via Kafka).
 * "This entire test run is finished."
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestRunCompletedEvent(
        String runId,
        String testSuiteId,
        String executionTargetId,
        RunStatus status,
        long durationMs,
        int totalCases,
        int passedCases,
        int failedCases,
        int skippedCases,
        Instant completedAt
) {
    public TestRunCompletedEvent {
        if (completedAt == null) {
            completedAt = Instant.now();
        }
    }
}
