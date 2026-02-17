package com.platform.testing.service.testcase.persistence.entity;

import com.platform.testing.service.testcase.domain.valueobject.TestCaseTypeEnum;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEST_CASE")
public class TestCaseEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEST_CASE_TYPE")
    private TestCaseTypeEnum testCaseType;

    @Column(name = "TEST_CASE_DATA_ID")
    private String testCaseDataId;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column()
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
