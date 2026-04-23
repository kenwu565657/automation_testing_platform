package com.platform.testing.admin.application.testcase;

import com.platform.testing.admin.application.testcase.dto.TestCaseResponse;
import com.platform.testing.domain.constant.TestType;
import com.platform.testing.domain.testcase.TestCase;
import com.platform.testing.domain.testcase.TestCaseId;
import com.platform.testing.domain.testcase.TestCaseRepository;
import com.platform.testing.domain.testsuite.TestSuiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetTestCaseUseCase {
    private final TestCaseRepository testCaseRepository;

    public TestCaseResponse getById(String id) {
        TestCase taseCase = testCaseRepository.findById(TestCaseId.of(id))
                .orElseThrow(() -> new IllegalArgumentException("TestCase not found: " + id));
        return TestCaseResponse.from(taseCase);
    }

    public List<TestCaseResponse> findByTestSuiteId(String testSuiteId) {
        return testCaseRepository
                .findByTestSuiteId(TestSuiteId.of(testSuiteId))
                .stream()
                .map(TestCaseResponse::from)
                .toList();
    }

    public List<TestCaseResponse> findByTag(String tag) {
        return testCaseRepository
                .findByTag(tag)
                .stream()
                .map(TestCaseResponse::from)
                .toList();
    }

    public List<TestCaseResponse> findByTestType(String testType) {
        return testCaseRepository
                .findByTestType(TestType.valueOf(testType))
                .stream()
                .map(TestCaseResponse::from)
                .toList();
    }
}
