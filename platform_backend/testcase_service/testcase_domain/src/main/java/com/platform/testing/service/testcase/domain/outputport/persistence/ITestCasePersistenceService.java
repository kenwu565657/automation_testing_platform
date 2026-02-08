package com.platform.testing.service.testcase.domain.outputport.persistence;

import java.util.List;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;

public interface ITestCasePersistenceService {
    List<TestCaseDomainModel> getTestCaseListByTestPlanId(String testPlanId);
    TestCaseDataDomainModel getTestCaseDataById(String testCaseDataId);

    boolean saveTestCaseDomainModel(TestCaseDomainModel testCaseDomainModel);
    boolean saveTestCaseDataDomainModel(TestCaseDataDomainModel testCaseDataDomainModel);
}
