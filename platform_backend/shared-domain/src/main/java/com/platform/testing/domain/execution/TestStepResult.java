package com.platform.testing.domain.execution;

import com.platform.testing.domain.common.ValueObject;
import com.platform.testing.domain.constant.RunStatus;

public record TestStepResult(
        String testStepId,
        int orderIndex,
        RunStatus status,
        long durationMs,
        String actualValue,
        String expectedValue,
        String errorMessage,
        String screenshotPath
) implements ValueObject {
    public static TestStepResult passed(String testStepId, int orderIndex, long durationMs, String actualValue, String expectedValue) {
        return new TestStepResult(testStepId, orderIndex, RunStatus.PASSED, durationMs, actualValue, expectedValue, null, null);
    }

    public static TestStepResult failed(String testStepId, int orderIndex, long durationMs, String actualValue, String expectedValue, String error, String screenshot) {
        return new TestStepResult(testStepId, orderIndex, RunStatus.FAILED, durationMs, actualValue, expectedValue, error, screenshot);
    }

    public static TestStepResult skipped(String testStepId, int orderIndex) {
        return new TestStepResult(testStepId, orderIndex, RunStatus.SKIPPED, 0, null, null, null, null);
    }
}
