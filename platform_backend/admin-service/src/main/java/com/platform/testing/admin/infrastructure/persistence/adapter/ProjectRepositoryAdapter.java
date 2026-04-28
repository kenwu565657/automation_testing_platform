package com.platform.testing.admin.infrastructure.persistence.adapter;

import com.platform.testing.admin.infrastructure.persistence.jpa.entity.ProjectJpaEntity;
import com.platform.testing.admin.infrastructure.persistence.jpa.repository.ProjectJpaRepository;
import com.platform.testing.admin.infrastructure.persistence.mapper.ProjectPersistenceMapper;
import com.platform.testing.domain.project.Project;
import com.platform.testing.domain.project.ProjectId;
import com.platform.testing.domain.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectPersistenceMapper projectPersistenceMapper;

    @Override
    public Project save(Project project) {
        ProjectJpaEntity projectJpaEntity = projectPersistenceMapper.toJpa(project);
        ProjectJpaEntity savedProjectJpaEntity = projectJpaRepository.save(projectJpaEntity);
        return projectPersistenceMapper.toDomain(savedProjectJpaEntity);
    }

    @Override
    public Optional<Project> findById(ProjectId id) {
        return projectJpaRepository
                .findById(id.value())
                .map(projectPersistenceMapper::toDomain);
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository
                .findAll()
                .stream()
                .map(projectPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(ProjectId id) {
        projectJpaRepository.deleteById(id.value());
    }
}
