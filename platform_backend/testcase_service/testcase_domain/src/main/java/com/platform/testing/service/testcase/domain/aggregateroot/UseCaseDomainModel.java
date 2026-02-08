package com.platform.testing.service.testcase.domain.aggregateroot;

import lombok.Data;

@Data
public class UseCaseDomainModel {
    private String id;
    private String name;
    private String description;
    private String testPlanId;
}
