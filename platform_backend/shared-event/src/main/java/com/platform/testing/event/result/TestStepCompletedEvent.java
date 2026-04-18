package com.platform.testing.event.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.platform.testing.domain.constant.RunStatus;
import java.time.Instant;
import java.util.Map;

/**
 * Engine → Report (via Kafka).
 * "This individual step has finished."
 * Published per-step for live dashboard streaming.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestStepCompletedEvent(
        String runId,
        String testCaseId,
        String testStepId,
        String stepName,
        int stepIndex,
        RunStatus status,
        long durationMs,
        String actualValue,
        String expectedValue,
        String errorMessage,
        String screenshotPath,
        Map<String, String> metadata,
        Instant completedAt
) {
    public TestStepCompletedEvent {
        if (metadata == null) {
            metadata = Map.of();
        }
        if (completedAt == null) {
            completedAt = Instant.now();
        }
    }
}