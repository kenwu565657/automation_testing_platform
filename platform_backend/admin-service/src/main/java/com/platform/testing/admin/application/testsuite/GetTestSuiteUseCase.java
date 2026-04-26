package com.platform.testing.admin.application.testsuite;

import com.platform.testing.admin.application.testsuite.dto.TestSuiteResponse;
import com.platform.testing.domain.project.ProjectId;
import com.platform.testing.domain.testsuite.TestSuiteId;
import com.platform.testing.domain.testsuite.TestSuiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetTestSuiteUseCase {

    private final TestSuiteRepository repository;

    public GetTestSuiteUseCase(TestSuiteRepository repository) {
        this.repository = repository;
    }

    public TestSuiteResponse getById(String id) {
        return TestSuiteResponse.from(
                repository.findById(TestSuiteId.of(id))
                        .orElseThrow(() -> new IllegalArgumentException("TestSuite not found: " + id))
        );
    }

    public List<TestSuiteResponse> getByProjectId(String projectId) {
        return repository.findByProjectId(ProjectId.of(projectId)).stream()
                .map(TestSuiteResponse::from).toList();
    }
}
