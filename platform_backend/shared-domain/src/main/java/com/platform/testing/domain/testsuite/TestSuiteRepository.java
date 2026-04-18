package com.platform.testing.domain.testsuite;

import com.platform.testing.domain.project.ProjectId;

import java.util.List;
import java.util.Optional;

public interface TestSuiteRepository {
    TestSuite save(TestSuite testSuite);
    Optional<TestSuite> findById(TestSuiteId id);
    List<TestSuite> findByProjectId(ProjectId projectId);
    void deleteById(TestSuiteId id);
}
