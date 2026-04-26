package com.platform.testing.admin.api.rest;

import com.platform.testing.admin.application.testsuite.CreateTestSuiteUseCase;
import com.platform.testing.admin.application.testsuite.GetTestSuiteUseCase;
import com.platform.testing.admin.application.testsuite.dto.CreateTestSuiteCommand;
import com.platform.testing.admin.application.testsuite.dto.TestSuiteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-suite")
public class TestSuiteController {

    private final CreateTestSuiteUseCase createUseCase;
    private final GetTestSuiteUseCase getUseCase;

    public TestSuiteController(CreateTestSuiteUseCase createUseCase, GetTestSuiteUseCase getUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestSuiteResponse create(@Valid @RequestBody CreateTestSuiteCommand command) {
        return createUseCase.execute(command);
    }

    @GetMapping("/{id}")
    public TestSuiteResponse get(@PathVariable String id) {
        return getUseCase.getById(id);
    }

    @GetMapping(params = "projectId")
    public List<TestSuiteResponse> listByProject(@RequestParam String projectId) {
        return getUseCase.getByProjectId(projectId);
    }
}
