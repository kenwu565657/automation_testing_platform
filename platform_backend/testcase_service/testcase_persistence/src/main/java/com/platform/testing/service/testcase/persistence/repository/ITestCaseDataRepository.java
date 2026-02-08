package com.platform.testing.service.testcase.persistence.repository;

import com.platform.testing.service.testcase.persistence.entity.TestCaseDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestCaseDataRepository extends JpaRepository<TestCaseDataEntity, String> {
}
