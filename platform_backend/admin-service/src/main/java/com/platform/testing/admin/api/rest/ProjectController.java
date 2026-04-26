package com.platform.testing.admin.api.rest;

import com.platform.testing.admin.application.project.CreateProjectUseCase;
import com.platform.testing.admin.application.project.GetProjectUseCase;
import com.platform.testing.admin.application.project.dto.CreateProjectCommand;
import com.platform.testing.admin.application.project.dto.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final CreateProjectUseCase createUseCase;
    private final GetProjectUseCase getUseCase;

    public ProjectController(CreateProjectUseCase createUseCase, GetProjectUseCase getUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody CreateProjectCommand command) {
        return createUseCase.execute(command);
    }

    @GetMapping
    public List<ProjectResponse> list() {
        return getUseCase.getAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse get(@PathVariable String id) {
        return getUseCase.getById(id);
    }
}
