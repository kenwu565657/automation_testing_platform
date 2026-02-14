package com.platform.testing.service.testcase.domain.service;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;
import com.platform.testing.service.testcase.domain.inputport.web.ITestCaseWebService;
import com.platform.testing.service.testcase.domain.outputport.persistence.ITestCasePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCaseDomainService implements ITestCaseWebService {
    private final ITestCasePersistenceService testCasePersistenceService;

    @Override
    public List<TestCaseDomainModel> getTestCaseListByTestPlanId(String testPlanId) {
        return testCasePersistenceService.getTestCaseListByTestPlanId(testPlanId);
    }

    @Override
    public TestCaseDataDomainModel getTestCaseDataById(String testCaseDataId) {
        return testCasePersistenceService.getTestCaseDataById(testCaseDataId);
    }

    @Override
    public boolean saveTestCaseDomainModel(TestCaseDomainModel testCaseDomainModel) {
        return testCasePersistenceService.saveTestCaseDomainModel(testCaseDomainModel);
    }

    @Override
    public boolean saveTestCaseDataDomainModel(TestCaseDataDomainModel testCaseDataDomainModel) {
        return testCasePersistenceService.saveTestCaseDataDomainModel(testCaseDataDomainModel);
    }
}
