package com.platform.testing.domain.testcase;

import com.platform.testing.domain.constant.TestType;
import com.platform.testing.domain.testsuite.TestSuiteId;

import java.util.List;
import java.util.Optional;

/**
 * DDD Repository Interface (Port).
 * Defined in domain layer, implemented by infrastructure.
 */
public interface TestCaseRepository {
    TestCase save(TestCase testCase);
    Optional<TestCase> findById(TestCaseId id);
    List<TestCase> findByTestSuiteId(TestSuiteId testSuiteId);
    List<TestCase> findByTag(String tag);
    List<TestCase> findByTestType(TestType testType);
    void deleteById(TestCaseId id);
}
