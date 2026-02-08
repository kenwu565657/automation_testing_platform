package com.platform.testing.service.testcase.persistence;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;
import com.platform.testing.service.testcase.domain.outputport.persistence.ITestCasePersistenceService;
import com.platform.testing.service.testcase.persistence.mapper.ITestCaseDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TestCasePersistenceService implements ITestCasePersistenceService {
    private final ITestCaseDataMapper testCaseDataMapper;
    private final ITestCasePersistenceService testCasePersistenceService;

    public TestCasePersistenceService(ITestCaseDataMapper testCaseDataMapper, ITestCasePersistenceService testCasePersistenceService) {
        this.testCaseDataMapper = testCaseDataMapper;
        this.testCasePersistenceService = testCasePersistenceService;
    }

    @Override
    public List<TestCaseDomainModel> getTestCaseListByTestPlanId(String testPlanId) {
        return List.of();
    }

    @Override
    public TestCaseDataDomainModel getTestCaseDataById(String testCaseDataId) {
        return null;
    }

    @Override
    public boolean saveTestCaseDomainModel(TestCaseDomainModel testCaseDomainModel) {
        return false;
    }

    @Override
    public boolean saveTestCaseDataDomainModel(TestCaseDataDomainModel testCaseDataDomainModel) {
        return false;
    }
}