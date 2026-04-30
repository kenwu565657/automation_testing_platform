package com.platform.testing.admin.infrastructure.persistence.adapter;

import com.platform.testing.admin.infrastructure.persistence.jpa.repository.TestCaseJpaRepository;
import com.platform.testing.admin.infrastructure.persistence.mapper.TestCasePersistenceMapper;
import com.platform.testing.domain.constant.TestType;
import com.platform.testing.domain.testcase.TestCase;
import com.platform.testing.domain.testcase.TestCaseId;
import com.platform.testing.domain.testcase.TestCaseRepository;
import com.platform.testing.domain.testsuite.TestSuiteId;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TestCaseRepositoryAdapter implements TestCaseRepository {

    private final TestCaseJpaRepository testCaseJpaRepository;
    private final TestCasePersistenceMapper testCasePersistenceMapper;

    public TestCaseRepositoryAdapter(TestCaseJpaRepository testCaseJpaRepository, TestCasePersistenceMapper testCasePersistenceMapper) {
        this.testCaseJpaRepository = testCaseJpaRepository;
        this.testCasePersistenceMapper = testCasePersistenceMapper;
    }

    @Override
    public TestCase save(TestCase testCase) {
        var jpa = testCasePersistenceMapper.toJpa(testCase);
        var saved = testCaseJpaRepository.save(jpa);
        return testCasePersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<TestCase> findById(TestCaseId id) {
        return testCaseJpaRepository.findByIdWithDetails(id.value()).map(testCasePersistenceMapper::toDomain);
    }

    @Override
    public List<TestCase> findByTestSuiteId(TestSuiteId testSuiteId) {
        return testCaseJpaRepository.findByTestSuiteIdAndActiveTrue(testSuiteId.value()).stream()
                .map(testCasePersistenceMapper::toDomain).toList();
    }

    @Override
    public List<TestCase> findByTag(String tag) {
        return testCaseJpaRepository.findByTag(tag).stream().map(testCasePersistenceMapper::toDomain).toList();
    }

    @Override
    public List<TestCase> findByTestType(TestType testType) {
        return testCaseJpaRepository.findByTestType(testType.name()).stream().map(testCasePersistenceMapper::toDomain).toList();
    }

    @Override
    public void deleteById(TestCaseId id) {
        testCaseJpaRepository.deleteById(id.value());
    }
}
