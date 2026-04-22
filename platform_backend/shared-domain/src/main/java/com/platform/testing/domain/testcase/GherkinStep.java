package com.platform.testing.domain.testcase;

import com.platform.testing.domain.constant.ActionType;
import com.platform.testing.domain.constant.GherkinKeyword;

import java.util.Map;
import java.util.Objects;

/**
 * DDD Value Object: A single Gherkin step definition.
 * Immutable. Contains both BDD text and engine execution metadata.
 */
public record GherkinStep(
        int orderIndex,
        String name,
        GherkinKeyword keyword,
        String stepText,
        ActionType actionType,
        Map<String, String> actionParameters,
        String targetElementId,
        StepAssertion assertion,
        VariableExtraction extraction,
        boolean background,
        boolean continueOnFailure,
        int waitBeforeMs,
        int waitAfterMs
) {
    public GherkinStep {
        Objects.requireNonNull(keyword);
        Objects.requireNonNull(stepText);
        Objects.requireNonNull(actionType);
        if (actionParameters == null) {
            actionParameters = Map.of();
        }
    }
}
