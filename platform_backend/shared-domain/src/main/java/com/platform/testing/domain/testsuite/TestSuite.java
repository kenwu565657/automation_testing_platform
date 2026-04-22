package com.platform.testing.domain.testsuite;

import com.platform.testing.domain.common.AggregateRoot;
import com.platform.testing.domain.project.ProjectId;
import com.platform.testing.domain.testcase.TestCaseId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TestSuite implements AggregateRoot {

    private final TestSuiteId id;
    private String name;
    private String description;
    private ProjectId projectId;
    private final List<TestCaseId> testCaseIds;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TestSuite create(String name, String description, ProjectId projectId) {
        return new TestSuite(TestSuiteId.generate(), name, description, projectId);
    }

    public static TestSuite reconstitute(TestSuiteId id, String name, String description,
                                         ProjectId projectId, List<TestCaseId> testCaseIds,
                                         boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        TestSuite ts = new TestSuite(id, name, description, projectId);
        ts.testCaseIds.addAll(testCaseIds);
        ts.active = active;
        ts.createdAt = createdAt;
        ts.updatedAt = updatedAt;
        return ts;
    }

    private TestSuite(TestSuiteId id, String name, String description, ProjectId projectId) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.projectId = Objects.requireNonNull(projectId);
        this.testCaseIds = new ArrayList<>();
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void addTestCase(TestCaseId testCaseId) {
        if (testCaseIds.contains(testCaseId)) {
            throw new IllegalArgumentException("TestCase already in suite: " + testCaseId.value());
        }
        testCaseIds.add(testCaseId);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeTestCase(TestCaseId testCaseId) {
        testCaseIds.remove(testCaseId);
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public TestSuiteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public List<TestCaseId> getTestCaseIds() {
        return Collections.unmodifiableList(testCaseIds);
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
