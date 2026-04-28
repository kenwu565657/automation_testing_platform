package com.platform.testing.admin.infrastructure.persistence.jpa.repository;

import com.platform.testing.admin.infrastructure.persistence.jpa.entity.ProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, String> {
}
