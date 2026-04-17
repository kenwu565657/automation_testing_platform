package com.platform.testing.domain.environment;

import com.platform.testing.domain.common.AggregateRoot;
import com.platform.testing.domain.project.ProjectId;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Environment implements AggregateRoot {

    private final EnvironmentId id;
    private final String name;
    private String baseUrl;
    private final ProjectId projectId;
    private final Map<String, String> variables;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Environment create(String name, String baseUrl, ProjectId projectId) {
        return new Environment(EnvironmentId.generate(), name, baseUrl, projectId);
    }

    public static Environment reconstitute(EnvironmentId id, String name, String baseUrl,
                                           ProjectId projectId, Map<String, String> variables,
                                           boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Environment env = new Environment(id, name, baseUrl, projectId);
        env.variables.putAll(variables);
        env.active = active;
        env.createdAt = createdAt;
        env.updatedAt = updatedAt;
        return env;
    }

    private Environment(EnvironmentId id, String name, String baseUrl, ProjectId projectId) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.baseUrl = baseUrl;
        this.projectId = Objects.requireNonNull(projectId);
        this.variables = new LinkedHashMap<>();
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void setVariable(String key, String value) {
        this.variables.put(key, value);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeVariable(String key) {
        this.variables.remove(key);
        this.updatedAt = LocalDateTime.now();
    }

    public void updateBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public EnvironmentId getId() { return id; }
    public String getName() { return name; }
    public String getBaseUrl() { return baseUrl; }
    public ProjectId getProjectId() { return projectId; }
    public Map<String, String> getVariables() { return Collections.unmodifiableMap(variables); }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
