package com.platform.testing.domain.execution;

import com.platform.testing.domain.environment.EnvironmentId;
import java.util.List;
import java.util.Optional;

public interface ExecutionTargetRepository {
    ExecutionTarget save(ExecutionTarget target);
    Optional<ExecutionTarget> findById(ExecutionTargetId id);
    List<ExecutionTarget> findByEnvironmentId(EnvironmentId environmentId);
    void deleteById(ExecutionTargetId id);
}
