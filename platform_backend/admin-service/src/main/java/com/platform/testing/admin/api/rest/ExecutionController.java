package com.platform.testing.admin.api.rest;

import com.platform.testing.admin.application.execution.TriggerExecutionUseCase;
import com.platform.testing.admin.application.execution.dto.TriggerExecutionCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/execution")
public class ExecutionController {

    private final TriggerExecutionUseCase triggerUseCase;

    public ExecutionController(TriggerExecutionUseCase triggerUseCase) {
        this.triggerUseCase = triggerUseCase;
    }

    @PostMapping("/trigger")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, String> trigger(@Valid @RequestBody TriggerExecutionCommand command) {
        String runId = triggerUseCase.execute(command);
        return Map.of("runId", runId, "status", "QUEUED");
    }
}
