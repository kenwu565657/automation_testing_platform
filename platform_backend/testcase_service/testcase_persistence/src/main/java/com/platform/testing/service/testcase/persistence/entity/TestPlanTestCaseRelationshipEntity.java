package com.platform.testing.service.testcase.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TEST_PLAN_TEST_CASE_RELATIONSHIP")
public class TestPlanTestCaseRelationshipEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name="TEST_PLAN_ID")
    private String testPlanId;

    @Column(name="TEST_CASE_ID")
    private String testCaseId;
}
