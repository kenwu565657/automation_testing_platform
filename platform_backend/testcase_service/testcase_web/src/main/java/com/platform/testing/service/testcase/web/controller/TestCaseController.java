package com.platform.testing.service.testcase.web.controller;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;
import com.platform.testing.service.testcase.domain.inputport.web.ITestCaseWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testcase")
public class TestCaseController {
    private final ITestCaseWebService testCaseWebService;

    @GetMapping("/list/{testPlanId}")
    public List<TestCaseDomainModel> getTestCaseListByTestPlanId(@PathVariable("testPlanId")  String testPlanId) {
        return testCaseWebService.getTestCaseListByTestPlanId(testPlanId);
    }

    @GetMapping("/data/{testCaseDataId}")
    public TestCaseDataDomainModel getTestCaseDataById(@PathVariable("testCaseDataId") String testCaseDataId) {
        return testCaseWebService.getTestCaseDataById(testCaseDataId);
    }

    @PostMapping("/save")
    public boolean saveTestCaseDomainModel(@RequestBody TestCaseDomainModel testCaseDomainModel) {
        return testCaseWebService.saveTestCaseDomainModel(testCaseDomainModel);
    }

    @PostMapping("/data/save")
    public boolean saveTestCaseDataDomainModel(@RequestBody TestCaseDataDomainModel testCaseDataDomainModel) {
        return testCaseWebService.saveTestCaseDataDomainModel(testCaseDataDomainModel);
    }
}
