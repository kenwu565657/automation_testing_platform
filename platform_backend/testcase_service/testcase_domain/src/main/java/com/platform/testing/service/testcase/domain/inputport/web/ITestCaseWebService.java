package com.platform.testing.service.testcase.domain.inputport.web;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;

import java.util.List;

public interface ITestCaseWebService {
    List<TestCaseDomainModel> getTestCaseListByTestPlanId(String testPlanId);
    TestCaseDataDomainModel getTestCaseDataById(String testCaseDataId);

    boolean saveTestCaseDomainModel(TestCaseDomainModel testCaseDomainModel);
    boolean saveTestCaseDataDomainModel(TestCaseDataDomainModel testCaseDataDomainModel);
}
