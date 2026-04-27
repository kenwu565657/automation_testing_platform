package com.platform.testing.admin.api.rest;

import com.platform.testing.admin.application.testcase.CreateTestCaseUseCase;
import com.platform.testing.admin.application.testcase.GetTestCaseUseCase;
import com.platform.testing.admin.application.testcase.UpdateTestCaseUseCase;
import com.platform.testing.admin.application.testcase.dto.CreateTestCaseCommand;
import com.platform.testing.admin.application.testcase.dto.TestCaseResponse;
import com.platform.testing.admin.application.testcase.dto.UpdateTestCaseCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-case")
@RequiredArgsConstructor
public class TestCaseController {

    private final CreateTestCaseUseCase createTestCaseUseCase;
    private final GetTestCaseUseCase getTestCaseUseCase;
    private final UpdateTestCaseUseCase updateTestCaseUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestCaseResponse create(@Valid @RequestBody CreateTestCaseCommand command) {
        return createTestCaseUseCase.execute(command);
    }

    @GetMapping("/{id}")
    public TestCaseResponse get(@PathVariable String id) {
        return getTestCaseUseCase.getById(id);
    }

    @GetMapping(params = "testSuiteId")
    public List<TestCaseResponse> listBySuite(@RequestParam String testSuiteId) {
        return getTestCaseUseCase.findByTestSuiteId(testSuiteId);
    }

    @GetMapping(params = "tag")
    public List<TestCaseResponse> listByTag(@RequestParam String tag) {
        return getTestCaseUseCase.findByTag(tag);
    }

    @GetMapping(params = "testType")
    public List<TestCaseResponse> listByType(@RequestParam String testType) {
        return getTestCaseUseCase.findByTestType(testType);
    }

    @PutMapping("/{id}")
    public TestCaseResponse update(@PathVariable String id, @Valid @RequestBody UpdateTestCaseCommand command) {
        return updateTestCaseUseCase.execute(id, command);
    }
}
