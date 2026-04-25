package com.platform.testing.admin.application.project.dto;

import com.platform.testing.domain.project.Project;
import java.time.LocalDateTime;

public record ProjectResponse(
        String id,
        String name,
        String description,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProjectResponse from(Project p) {
        return new ProjectResponse(
                p.getId().value(), p.getName(), p.getDescription(),
                p.isActive(), p.getCreatedAt(), p.getUpdatedAt()
        );
    }
}
