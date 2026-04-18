package com.platform.testing.event.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;

/**
 * Engine → Report (via Kafka).
 * "This specific test case has started."
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestCaseStartedEvent(
        String runId,
        String testCaseId,
        String testCaseName,
        String executionTargetId,
        int totalSteps,
        Instant startedAt
) {
    public TestCaseStartedEvent {
        if (startedAt == null) {
            startedAt = Instant.now();
        }
    }
}
