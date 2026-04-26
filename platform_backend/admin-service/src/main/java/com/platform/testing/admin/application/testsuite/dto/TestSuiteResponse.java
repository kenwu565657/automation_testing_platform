package com.platform.testing.admin.application.testsuite.dto;

import com.platform.testing.domain.testcase.TestCaseId;
import com.platform.testing.domain.testsuite.TestSuite;
import java.time.LocalDateTime;
import java.util.List;

public record TestSuiteResponse(
        String id,
        String name,
        String description,
        String projectId,
        List<String> testCaseIds,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TestSuiteResponse from(TestSuite ts) {
        return new TestSuiteResponse(
                ts.getId().value(), ts.getName(), ts.getDescription(),
                ts.getProjectId().value(),
                ts.getTestCaseIds().stream().map(TestCaseId::value).toList(),
                ts.isActive(), ts.getCreatedAt(), ts.getUpdatedAt()
        );
    }
}
