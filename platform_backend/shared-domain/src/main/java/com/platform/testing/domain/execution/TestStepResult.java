package com.platform.testing.domain.execution;

import com.platform.testing.domain.common.ValueObject;
import com.platform.testing.domain.constant.RunStatus;
import com.platform.testing.domain.constant.StepStatus;

import java.time.Instant;

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
    public static TestStepResult passed(int idx, String text, String keyword, long durationMs) {
        return new TestStepResult(idx, text, keyword, StepStatus.PASSED, durationMs, null, null, Instant.now());
    }

    public static TestStepResult failed(int idx, String text, String keyword,
                                    long durationMs, String error, String screenshot) {
        return new TestStepResult(idx, text, keyword, StepStatus.FAILED, durationMs, error, screenshot, Instant.now());
    }

    public static TestStepResult skipped(int idx, String text, String keyword) {
        return new TestStepResult(idx, text, keyword, StepStatus.SKIPPED, 0, null, null, Instant.now());
    }
}
