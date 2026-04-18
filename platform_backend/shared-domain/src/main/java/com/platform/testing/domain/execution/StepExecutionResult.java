package com.platform.testing.domain.execution;

import com.platform.testing.domain.constant.StepStatus;

import java.time.Instant;

/**
 * Immutable Value Object — result of a single step execution.
 */
public record StepExecutionResult(
        int stepIndex,
        String stepText,
        String keyword,
        StepStatus status,
        long durationMillis,
        String errorMessage,
        String screenshotBase64,
        Instant timestamp
) {
    public static StepExecutionResult passed(int stepIndex, String stepText,
                                             String keyword, long durationMillis) {
        return new StepExecutionResult(
                stepIndex, stepText, keyword, StepStatus.PASSED,
                durationMillis, null, null, Instant.now()
        );
    }

    public static StepExecutionResult failed(int stepIndex, String stepText,
                                             String keyword, long durationMillis,
                                             String error, String screenshot) {
        return new StepExecutionResult(
                stepIndex, stepText, keyword, StepStatus.FAILED,
                durationMillis, error, screenshot, Instant.now()
        );
    }

    public static StepExecutionResult skipped(int stepIndex, String stepText, String keyword) {
        return new StepExecutionResult(
                stepIndex, stepText, keyword, StepStatus.SKIPPED,
                0, null, null, Instant.now()
        );
    }

    public JsonObject toJson() {
        return new JsonObject()
                .put("stepIndex", stepIndex)
                .put("stepText", stepText)
                .put("keyword", keyword)
                .put("status", status.name())
                .put("durationMillis", durationMillis)
                .put("errorMessage", errorMessage)
                .put("timestamp", timestamp.toString());
    }
}
