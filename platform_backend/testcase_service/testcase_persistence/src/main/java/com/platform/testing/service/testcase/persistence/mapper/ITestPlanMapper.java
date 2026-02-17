package com.platform.testing.service.testcase.persistence.mapper;

import com.platform.testing.service.testcase.domain.aggregateroot.TestPlanDomainModel;
import com.platform.testing.service.testcase.persistence.entity.TestPlanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestPlanMapper {
    TestPlanEntity mapTestPlanDomainModelToTestPlanEntity(TestPlanDomainModel testPlanDomainModel);
    TestPlanDomainModel mapTestPlanEntityToTestPlanDomainModel(TestPlanEntity testPlanEntity);
}
