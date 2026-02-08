package com.platform.testing.service.testcase.domain.aggregateroot;

import com.platform.testing.service.testcase.domain.valueobject.TestCaseTypeEnum;
import lombok.Data;

@Data
public class TestCaseDomainModel {
    private String id;
    private String name;
    private String description;
    private TestCaseTypeEnum testCaseType;
    private String testCaseDataId;
    private String useCaseId;
    private String testPlanId;
}
