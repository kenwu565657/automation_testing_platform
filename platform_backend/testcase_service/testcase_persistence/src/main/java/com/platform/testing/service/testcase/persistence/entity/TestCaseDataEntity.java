package com.platform.testing.service.testcase.persistence.entity;

import com.platform.testing.service.testcase.domain.valueobject.TestCaseDataTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEST_CASE_DATA")
public class TestCaseDataEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEST_CASE_DATA_TYPE")
    private TestCaseDataTypeEnum testCaseDataType;

    @Column(name = "DATA_CONTENT")
    private String dataContent;

    @Column(name = "REMOTE_DOWNLOAD_URL")
    private String remoteDownloadUrl;
}
