package com.platform.testing.event.execution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;

/**
 * Engine → Report (via Kafka).
 * "This test run has started executing."
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestRunStartedEvent(
        String runId,
        String testSuiteId,
        String executionTargetId,
        String environmentId,
        int totalCases,
        Instant startedAt
) {
    public TestRunStartedEvent {
        if (startedAt == null) startedAt = Instant.now();
    }
}
