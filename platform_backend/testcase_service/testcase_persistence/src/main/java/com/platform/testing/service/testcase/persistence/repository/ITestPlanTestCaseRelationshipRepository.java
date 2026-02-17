package com.platform.testing.service.testcase.persistence.repository;

import com.platform.testing.service.testcase.persistence.entity.TestPlanTestCaseRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITestPlanTestCaseRelationshipRepository extends JpaRepository<TestPlanTestCaseRelationshipEntity, String> {
    @Query("select t.testCaseId from TestPlanTestCaseRelationshipEntity t where t.testPlanId = :testPlanId")
    List<String> findTestCaseIdsByTestPlanId(@Param("testPlanId") String testPlanId);
}
