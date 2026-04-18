package com.platform.testing.event.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;

import java.time.Instant;
import java.util.Set;

/**
 * Admin → anyone (via Kafka).
 * "A new test case was created."
 * Report service can use this to index test case metadata for search.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TestCaseCreatedEvent(
        String testCaseId,
        String testCaseName,
        String testSuiteId,
        TestType testType,
        Priority priority,
        Set<String> tags,
        String createdBy,
        Instant createdAt
) {
    public TestCaseCreatedEvent {
        if (tags == null) {
            tags = Set.of();
        }

        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
