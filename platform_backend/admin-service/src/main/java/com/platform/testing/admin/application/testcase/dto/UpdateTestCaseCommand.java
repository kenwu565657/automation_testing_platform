package com.platform.testing.admin.application.testcase.dto;

import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

public record UpdateTestCaseCommand(
        String name,
        String description,
        String featureTitle,
        String scenarioTitle,
        TestType testType,
        Priority priority,
        String testSuiteId,
        @Valid List<GherkinStepCommand> testStepList,
        Set<String> tagSet,
        Integer timeoutSeconds,
        Integer retryCount
) {}
