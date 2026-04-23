package com.platform.testing.admin.application.testcase;

import com.platform.testing.admin.application.testcase.dto.TestCaseResponse;
import com.platform.testing.admin.application.testcase.dto.UpdateTestCaseCommand;
import com.platform.testing.domain.testcase.GherkinStep;
import com.platform.testing.domain.testcase.StepAssertion;
import com.platform.testing.domain.testcase.TestCase;
import com.platform.testing.domain.testcase.TestCaseId;
import com.platform.testing.domain.testcase.TestCaseRepository;
import com.platform.testing.domain.testcase.VariableExtraction;
import com.platform.testing.domain.testsuite.TestSuiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTestCaseUseCase {

    private final TestCaseRepository testCaseRepository;

    @Transactional
    public TestCaseResponse execute(String id, UpdateTestCaseCommand updateTestCaseCommand) {
        TestCase testCase = testCaseRepository.findById(TestCaseId.of(id))
                .orElseThrow(() -> new IllegalArgumentException("TestCase not found: " + id));

        if (updateTestCaseCommand.description() != null) {
            testCase.updateDescription(updateTestCaseCommand.description());
        }

        if (updateTestCaseCommand.testSuiteId() != null) {
            testCase.assignToSuite(TestSuiteId.of(updateTestCaseCommand.testSuiteId()));
        }

        // Replace steps if provided
        if (updateTestCaseCommand.testStepList() != null && !updateTestCaseCommand.testStepList().isEmpty()) {
            var newSteps = updateTestCaseCommand.testStepList().stream().map(gherkinStepCommand -> {
                StepAssertion stepAssertion = null;
                if (gherkinStepCommand.assertionType() != null) {
                    stepAssertion = new StepAssertion(
                            gherkinStepCommand.assertionType(), gherkinStepCommand.assertionOperator(),
                            gherkinStepCommand.expectedValue(), gherkinStepCommand.actualValueSource()
                    );
                }

                VariableExtraction variableExtraction = null;
                if (gherkinStepCommand.extractionVarName() != null) {
                    variableExtraction = new VariableExtraction(
                            gherkinStepCommand.extractionVarName(), gherkinStepCommand.extractionSource(),
                            gherkinStepCommand.extractionExpression()
                    );
                }

                return new GherkinStep(
                        gherkinStepCommand.orderIndex(),
                        gherkinStepCommand.name(),
                        gherkinStepCommand.keyword(),
                        gherkinStepCommand.stepText(),
                        gherkinStepCommand.actionType(),
                        gherkinStepCommand.actionParameters(),
                        gherkinStepCommand.targetElementId(),
                        stepAssertion,
                        variableExtraction,
                        gherkinStepCommand.background(),
                        gherkinStepCommand.continueOnFailure(),
                        gherkinStepCommand.waitBeforeMs(),
                        gherkinStepCommand.waitAfterMs()
                );
            }).toList();
            testCase.replaceSteps(newSteps);
        }

        // Replace tags if provided
        if (updateTestCaseCommand.tagSet() != null) {
            // Clear and re-add
            testCase.getTags().forEach(testCase::removeTag);
            updateTestCaseCommand.tagSet().forEach(testCase::addTag);
        }

        TestCase saved = testCaseRepository.save(testCase);
        return TestCaseResponse.from(saved);
    }
}
