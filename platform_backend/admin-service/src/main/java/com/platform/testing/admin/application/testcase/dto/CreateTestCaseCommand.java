package com.platform.testing.admin.application.testcase.dto;

import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public record CreateTestCaseCommand(
        @NotBlank String name,
        String description,
        @NotBlank String featureTitle,
        @NotBlank String scenarioTitle,
        @NotNull TestType testType,
        Priority priority,
        String testSuiteId,
        @Valid List<GherkinStepCommand> testStepList,
        Set<String> tagSet,
        int timeoutSeconds,
        int retryCount,
        String createdBy
) {
    public CreateTestCaseCommand {
        if (testStepList == null) {
            testStepList = List.of();
        }

        if (tagSet == null) {
            tagSet = Set.of();
        }

        if (timeoutSeconds <= 0) {
            timeoutSeconds = 60;
        }
    }
}
