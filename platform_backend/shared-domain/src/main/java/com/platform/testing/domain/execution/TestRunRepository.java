package com.platform.testing.domain.execution;

import com.platform.testing.domain.constant.RunStatus;
import com.platform.testing.domain.testsuite.TestSuiteId;
import java.util.List;
import java.util.Optional;

public interface TestRunRepository {
    TestRun save(TestRun testRun);
    Optional<TestRun> findById(TestRunId id);
    List<TestRun> findByTestSuiteId(TestSuiteId testSuiteId);
    List<TestRun> findByStatus(RunStatus status);
    void deleteById(TestRunId id);
}
