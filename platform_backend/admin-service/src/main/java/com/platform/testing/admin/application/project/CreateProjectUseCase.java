package com.platform.testing.admin.application.project;

import com.platform.testing.admin.application.project.dto.CreateProjectCommand;
import com.platform.testing.admin.application.project.dto.ProjectResponse;
import com.platform.testing.domain.project.Project;
import com.platform.testing.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProjectUseCase {

    private final ProjectRepository repository;

    public CreateProjectUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProjectResponse execute(CreateProjectCommand command) {
        Project project = Project.create(command.name(), command.description());
        Project saved = repository.save(project);
        return ProjectResponse.from(saved);
    }
}