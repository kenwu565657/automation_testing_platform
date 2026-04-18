package com.platform.testing.domain.execution;

import com.platform.testing.domain.constant.StepStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("stepIndex", stepIndex);
        map.put("stepText", stepText);
        map.put("keyword", keyword);
        map.put("status", status.name());
        map.put("durationMillis", durationMillis);
        map.put("errorMessage", errorMessage);
        map.put("timestamp", timestamp.toString());
        return map;
    }
}
