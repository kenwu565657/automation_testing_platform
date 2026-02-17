package com.platform.testing.service.testcase.persistence.repository;

import com.platform.testing.service.testcase.persistence.entity.TestCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITestCaseRepository extends JpaRepository<TestCaseEntity, String> {
    TestCaseEntity getTestCaseById(String id);
    List<TestCaseEntity> getTestCaseListByIdIn(List<String> idList);
}
