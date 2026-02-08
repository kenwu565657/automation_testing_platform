package com.platform.testing.service.testcase.domain.aggregateroot;

import com.platform.testing.service.testcase.domain.valueobject.TestCaseDataTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseDataDomainModel {
    private String id;
    private String name;
    private String description;
    private TestCaseDataTypeEnum testCaseDataType;
    private String dataContent;
    private String remoteDownloadUrl;
}
