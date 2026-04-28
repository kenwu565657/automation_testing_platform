package com.platform.testing.admin.infrastructure.persistence.mapper;

import com.platform.testing.admin.infrastructure.persistence.jpa.entity.ProjectJpaEntity;
import com.platform.testing.domain.project.Project;
import com.platform.testing.domain.project.ProjectId;
import org.springframework.stereotype.Component;

@Component
public class ProjectPersistenceMapper {

    public ProjectJpaEntity toJpa(Project domain) {
        return ProjectJpaEntity.builder()
                .id(domain.getId().value())
                .name(domain.getName())
                .description(domain.getDescription())
                .active(domain.isActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public Project toDomain(ProjectJpaEntity jpa) {
        return Project.reconstitute(
                ProjectId.of(jpa.getId()),
                jpa.getName(),
                jpa.getDescription(),
                jpa.isActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt()
        );
    }
}
