package com.platform.testing.service.testcase.persistence.repository;

import com.platform.testing.service.testcase.persistence.entity.TestCaseEntity;
import com.platform.testing.service.testcase.persistence.entity.TestPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITestPlanRepository extends JpaRepository<TestPlanEntity, String> {
    TestPlanEntity getTestPlanById(String id);
    List<TestPlanEntity> getTestPlanListByIdIn(List<String> idList);
}
