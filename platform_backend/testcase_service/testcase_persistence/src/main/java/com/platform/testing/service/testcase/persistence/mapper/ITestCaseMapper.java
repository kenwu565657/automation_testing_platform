package com.platform.testing.service.testcase.persistence.mapper;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;
import com.platform.testing.service.testcase.persistence.entity.TestCaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestCaseMapper {
    TestCaseEntity mapTestCaseDomainModelToTestCaseEntity(TestCaseDomainModel testCaseDomainModel);
    TestCaseDomainModel mapTestCaseEntityToTestCaseDomainModel(TestCaseEntity testCaseEntity);
}
