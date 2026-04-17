package com.platform.testing.domain.pageobject;

import com.platform.testing.domain.common.AggregateRoot;
import com.platform.testing.domain.project.ProjectId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PageObject implements AggregateRoot {

    private final PageObjectId id;
    private String name;
    private String description;
    private String pageUrl;
    private ProjectId projectId;
    private final List<PageElement> elements;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PageObject create(String name, String description, String pageUrl, ProjectId projectId) {
        return new PageObject(PageObjectId.generate(), name, description, pageUrl, projectId);
    }

    public static PageObject reconstitute(PageObjectId id, String name, String description,
                                          String pageUrl, ProjectId projectId, List<PageElement> elements,
                                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        PageObject po = new PageObject(id, name, description, pageUrl, projectId);
        po.elements.addAll(elements);
        po.createdAt = createdAt;
        po.updatedAt = updatedAt;
        return po;
    }

    private PageObject(PageObjectId id, String name, String description, String pageUrl, ProjectId projectId) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.pageUrl = pageUrl;
        this.projectId = Objects.requireNonNull(projectId);
        this.elements = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void addElement(PageElement element) {
        boolean nameExists = elements.stream().anyMatch(e -> e.getName().equals(element.getName()));
        if (nameExists) {
            throw new IllegalArgumentException("Element with name already exists: " + element.getName());
        }
        elements.add(element);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeElement(String elementId) {
        elements.removeIf(e -> e.getId().equals(elementId));
        this.updatedAt = LocalDateTime.now();
    }

    public Optional<PageElement> findElementByName(String name) {
        return elements.stream().filter(e -> e.getName().equals(name)).findFirst();
    }

    public PageObjectId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPageUrl() { return pageUrl; }
    public ProjectId getProjectId() { return projectId; }
    public List<PageElement> getElements() { return Collections.unmodifiableList(elements); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
