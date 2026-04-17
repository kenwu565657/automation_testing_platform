package com.platform.testing.domain.project;

import com.platform.testing.domain.common.AggregateRoot;
import java.time.LocalDateTime;
import java.util.Objects;

public class Project implements AggregateRoot {
    private final ProjectId id;
    private String name;
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Project create(String name, String description) {
        return new Project(ProjectId.generate(), name, description);
    }

    public static Project reconstitute(ProjectId id, String name, String description,
                                       boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Project p = new Project(id, name, description);
        p.active = active;
        p.createdAt = createdAt;
        p.updatedAt = updatedAt;
        return p;
    }

    private Project(ProjectId id, String name, String description) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void rename(String newName) {
        this.name = Objects.requireNonNull(newName);
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public ProjectId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
