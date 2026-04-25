package com.platform.testing.admin.application.project;

import com.platform.testing.admin.application.project.dto.ProjectResponse;
import com.platform.testing.domain.project.Project;
import com.platform.testing.domain.project.ProjectId;
import com.platform.testing.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetProjectUseCase {

    private final ProjectRepository repository;

    public GetProjectUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectResponse getById(String id) {
        Project project = repository.findById(ProjectId.of(id))
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + id));
        return ProjectResponse.from(project);
    }

    public List<ProjectResponse> getAll() {
        return repository.findAll().stream()
                .map(ProjectResponse::from)
                .toList();
    }
}
