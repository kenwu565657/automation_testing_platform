package com.platform.testing.admin.application.execution.dto;

import com.platform.testing.domain.constant.Priority;
import jakarta.validation.constraints.NotBlank;

public record TriggerExecutionCommand(
        @NotBlank String testSuiteId,
        @NotBlank String executionTargetId,
        @NotBlank String environmentId,
        Priority priority,
        String triggeredBy
) {}
