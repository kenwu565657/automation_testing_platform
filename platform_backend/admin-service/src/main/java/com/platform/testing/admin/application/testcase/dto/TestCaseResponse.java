package com.platform.testing.admin.application.testcase.dto;

import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;
import com.platform.testing.domain.testcase.GherkinStep;
import com.platform.testing.domain.testcase.TestCase;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record TestCaseResponse(
        String id,
        String name,
        String description,
        String featureTitle,
        String scenarioTitle,
        TestType testType,
        Priority priority,
        String testSuiteId,
        List<StepResponse> steps,
        Set<String> tags,
        boolean active,
        int timeoutSeconds,
        int retryCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdBy
) {
    public static TestCaseResponse from(TestCase tc) {
        return new TestCaseResponse(
                tc.getId().value(),
                tc.getName(),
                tc.getDescription(),
                tc.getFeatureTitle(),
                tc.getScenarioTitle(),
                tc.getTestType(),
                tc.getPriority(),
                tc.getTestSuiteId() != null ? tc.getTestSuiteId().value() : null,
                tc.getSteps().stream().map(StepResponse::from).toList(),
                tc.getTags(),
                tc.isActive(),
                tc.getTimeoutSeconds(),
                tc.getRetryCount(),
                tc.getCreatedAt(),
                tc.getUpdatedAt(),
                tc.getCreatedBy()
        );
    }

    public record StepResponse(
            int orderIndex,
            String name,
            String keyword,
            String stepText,
            String actionType
    ) {
        public static StepResponse from(GherkinStep s) {
            return new StepResponse(
                    s.orderIndex(), s.name(),
                    s.keyword().name(), s.stepText(), s.actionType().name()
            );
        }
    }
}
