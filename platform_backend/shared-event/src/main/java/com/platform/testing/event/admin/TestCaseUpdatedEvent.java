package com.platform.testing.event.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;

import java.time.Instant;
import java.util.Set;

/**
 * Admin → anyone (via Kafka).
 * "An existing test case was modified."
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestCaseUpdatedEvent(
        String testCaseId,
        String testCaseName,
        String testSuiteId,
        TestType testType,
        Priority priority,
        Set<String> tags,
        int stepCount,
        boolean active,
        Instant updatedAt
) {
    public TestCaseUpdatedEvent {
        if (tags == null) {
            tags = Set.of();
        }

        if (updatedAt == null) {
            updatedAt = Instant.now();
        }
    }
}
