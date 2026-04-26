package com.platform.testing.admin.application.testsuite;

import com.platform.testing.admin.application.testsuite.dto.CreateTestSuiteCommand;
import com.platform.testing.admin.application.testsuite.dto.TestSuiteResponse;
import com.platform.testing.domain.project.ProjectId;
import com.platform.testing.domain.testcase.TestCaseId;
import com.platform.testing.domain.testsuite.TestSuite;
import com.platform.testing.domain.testsuite.TestSuiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTestSuiteUseCase {

    private final TestSuiteRepository repository;

    public CreateTestSuiteUseCase(TestSuiteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TestSuiteResponse execute(CreateTestSuiteCommand cmd) {
        TestSuite suite = TestSuite.create(cmd.name(), cmd.description(), ProjectId.of(cmd.projectId()));
        cmd.testCaseIds().forEach(id -> suite.addTestCase(TestCaseId.of(id)));
        TestSuite saved = repository.save(suite);
        return TestSuiteResponse.from(saved);
    }
}
