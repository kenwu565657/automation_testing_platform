package com.platform.testing.service.testcase.persistence.mapper;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.persistence.entity.TestCaseDataEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestCaseDataMapper {
    TestCaseDataEntity mapTestCaseDataDomainModelToTestCaseDataEntity(TestCaseDataDomainModel testCaseDataDomainModel);
    TestCaseDataDomainModel mapTestCaseDataEntityToTestCaseDataDomainModel(TestCaseDataEntity testCaseDataEntity);
}
