package com.platform.testing.admin.application.testcase;

import com.platform.testing.admin.application.testcase.dto.CreateTestCaseCommand;
import com.platform.testing.admin.application.testcase.dto.TestCaseResponse;
import com.platform.testing.domain.testcase.GherkinStep;
import com.platform.testing.domain.testcase.StepAssertion;
import com.platform.testing.domain.testcase.TestCase;
import com.platform.testing.domain.testcase.TestCaseRepository;
import com.platform.testing.domain.testcase.VariableExtraction;
import com.platform.testing.domain.testsuite.TestSuiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTestCaseUseCase {
    private final TestCaseRepository testCaseRepository;

    @Transactional
    public TestCaseResponse execute(CreateTestCaseCommand createTestCaseCommand) {
        // 1. Create aggregate
        TestCase testCase = TestCase.create(
                createTestCaseCommand.name(),
                createTestCaseCommand.featureTitle(),
                createTestCaseCommand.scenarioTitle(),
                createTestCaseCommand.testType(),
                createTestCaseCommand.priority(),
                createTestCaseCommand.createdBy()
        );

        // 2. Description
        if (createTestCaseCommand.description() != null) {
            testCase.updateDescription(createTestCaseCommand.description());
        }

        // 3. Assign to suite
        if (createTestCaseCommand.testSuiteId() != null) {
            testCase.assignToSuite(TestSuiteId.of(createTestCaseCommand.testSuiteId()));
        }

        // 4. Add steps
        createTestCaseCommand.testStepList().forEach(testCaseStepCommand -> {
            StepAssertion stepAssertion = null;
            if (testCaseStepCommand.assertionType() != null) {
                stepAssertion = new StepAssertion(
                        testCaseStepCommand.assertionType(), testCaseStepCommand.assertionOperator(),
                        testCaseStepCommand.expectedValue(), testCaseStepCommand.actualValueSource()
                );
            }

            VariableExtraction variableExtraction = null;
            if (testCaseStepCommand.extractionVarName() != null) {
                variableExtraction = new VariableExtraction(
                        testCaseStepCommand.extractionVarName(), testCaseStepCommand.extractionSource(),
                        testCaseStepCommand.extractionExpression()
                );
            }

            testCase.addStep(new GherkinStep(
                    testCaseStepCommand.orderIndex(),
                    testCaseStepCommand.name(),
                    testCaseStepCommand.keyword(),
                    testCaseStepCommand.stepText(),
                    testCaseStepCommand.actionType(),
                    testCaseStepCommand.actionParameters(),
                    testCaseStepCommand.targetElementId(),
                    stepAssertion,
                    variableExtraction,
                    testCaseStepCommand.background(),
                    testCaseStepCommand.continueOnFailure(),
                    testCaseStepCommand.waitBeforeMs(),
                    testCaseStepCommand.waitAfterMs()
            ));
        });

        // 5. Add tags
        createTestCaseCommand.tagSet().forEach(testCase::addTag);

        // 6. Save
        TestCase saved = testCaseRepository.save(testCase);
        return TestCaseResponse.from(saved);
    }
}
