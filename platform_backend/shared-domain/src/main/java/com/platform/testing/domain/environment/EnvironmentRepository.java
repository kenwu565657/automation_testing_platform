package com.platform.testing.domain.environment;

import com.platform.testing.domain.project.ProjectId;

import java.util.List;
import java.util.Optional;

public interface EnvironmentRepository {
    Environment save(Environment environment);
    Optional<Environment> findById(EnvironmentId id);
    List<Environment> findByProjectId(ProjectId projectId);
    void deleteById(EnvironmentId id);
}
