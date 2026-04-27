package com.platform.testing.admin.application.execution;

import com.platform.testing.admin.application.execution.dto.TriggerExecutionCommand;
import com.platform.testing.admin.infrastructure.kafka.ExecutionRequestProducer;
import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.execution.TestRun;
import com.platform.testing.domain.execution.TestRunRepository;
import com.platform.testing.domain.execution.ExecutionTargetId;
import com.platform.testing.domain.environment.EnvironmentId;
import com.platform.testing.domain.testcase.TestCase;
import com.platform.testing.domain.testcase.TestCaseRepository;
import com.platform.testing.domain.testsuite.TestSuite;
import com.platform.testing.domain.testsuite.TestSuiteId;
import com.platform.testing.domain.testsuite.TestSuiteRepository;
import com.platform.testing.event.execution.TestExecutionRequestEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TriggerExecutionUseCase {

    private final TestSuiteRepository testSuiteRepository;
    private final TestCaseRepository testCaseRepository;
    private final TestRunRepository testRunRepository;
    private final ExecutionRequestProducer kafkaProducer;

    @Transactional
    public String execute(TriggerExecutionCommand cmd) {
        // 1. Load suite
        TestSuite testSuite = testSuiteRepository.findById(TestSuiteId.of(cmd.testSuiteId()))
                .orElseThrow(() -> new IllegalArgumentException("TestSuite not found: " + cmd.testSuiteId()));

        // 2. Create TestRun aggregate
        TestRun testRun = TestRun.create(
                TestSuiteId.of(cmd.testSuiteId()),
                ExecutionTargetId.of(cmd.executionTargetId()),
                EnvironmentId.of(cmd.environmentId()),
                cmd.triggeredBy()
        );
        testRunRepository.save(testRun);

        // 3. Publish one Kafka event per test case in the suite
        for (var testCaseId : testSuite.getTestCaseIds()) {
            TestCase testCase = testCaseRepository.findById(testCaseId)
                    .orElseThrow(() -> new IllegalArgumentException("TestCase not found: " + testCaseId.value()));

            kafkaProducer.send(new TestExecutionRequestEvent(
                    testRun.getId().value(),
                    testCaseId.value(),
                    cmd.testSuiteId(),
                    cmd.environmentId(),
                    cmd.executionTargetId(),
                    cmd.triggeredBy(),
                    testCase.getTestType(),
                    cmd.priority() != null ? cmd.priority() : Priority.MEDIUM,
                    testCase.getTimeoutSeconds(),
                    testCase.getRetryCount(),
                    Instant.now()
            ));
        }

        return testRun.getId().value();
    }
}
