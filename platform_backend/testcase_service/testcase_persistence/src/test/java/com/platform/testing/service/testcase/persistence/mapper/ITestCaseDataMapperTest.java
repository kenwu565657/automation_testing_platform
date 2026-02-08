package com.platform.testing.service.testcase.persistence.mapper;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.valueobject.TestCaseDataTypeEnum;
import com.platform.testing.service.testcase.persistence.entity.TestCaseDataEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ITestCaseDataMapperTest {

    private final ITestCaseDataMapper mapper = Mappers.getMapper(ITestCaseDataMapper.class);

    @Test
    void mapTestCaseDataDomainModelToTestCaseDataEntity() {
        TestCaseDataDomainModel domainModel = TestCaseDataDomainModel.builder()
                .id("1")
                .name("Test Case 1")
                .description("Test Description")
                .testCaseDataType(TestCaseDataTypeEnum.JSON_STRING)
                .dataContent("{\"key\": \"value\"}")
                .remoteDownloadUrl("example.com/testcase1.json")
                .build();

        TestCaseDataEntity entity = mapper.mapTestCaseDataDomainModelToTestCaseDataEntity(domainModel);

        assertNotNull(entity);
        assertEquals(domainModel.getId(), entity.getId());
        assertEquals(domainModel.getName(), entity.getName());
        assertEquals(domainModel.getDescription(), entity.getDescription());
        assertEquals(domainModel.getTestCaseDataType(), entity.getTestCaseDataType());
        assertEquals(domainModel.getDataContent(), entity.getDataContent());
        assertEquals(domainModel.getRemoteDownloadUrl(), entity.getRemoteDownloadUrl());
    }

    @Test
    void mapTestCaseDataEntityToTestCaseDataDomainModel() {
        TestCaseDataEntity entity = TestCaseDataEntity.builder()
                .id("1")
                .name("Test Case 1")
                .description("Test Description")
                .testCaseDataType(TestCaseDataTypeEnum.JSON_STRING)
                .dataContent("{\"key\": \"value\"}")
                .remoteDownloadUrl("example.com/testcase1.json")
                .build();

        TestCaseDataDomainModel domainModel = mapper.mapTestCaseDataEntityToTestCaseDataDomainModel(entity);

        assertNotNull(domainModel);
        assertEquals(entity.getId(), domainModel.getId());
        assertEquals(entity.getName(), domainModel.getName());
        assertEquals(entity.getDescription(), domainModel.getDescription());
        assertEquals(entity.getTestCaseDataType(), domainModel.getTestCaseDataType());
        assertEquals(entity.getDataContent(), domainModel.getDataContent());
        assertEquals(entity.getRemoteDownloadUrl(), domainModel.getRemoteDownloadUrl());
    }

    @Test
    void shouldReturnNullWhenInputIsNull() {
        assertNull(mapper.mapTestCaseDataDomainModelToTestCaseDataEntity(null));
        assertNull(mapper.mapTestCaseDataEntityToTestCaseDataDomainModel(null));
    }
}
