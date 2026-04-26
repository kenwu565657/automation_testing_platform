package com.platform.testing.admin.application.testsuite.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CreateTestSuiteCommand(
        @NotBlank String name,
        String description,
        @NotBlank String projectId,
        List<String> testCaseIds
) {
    public CreateTestSuiteCommand {
        if (testCaseIds == null) {
            testCaseIds = List.of();
        }
    }
}
