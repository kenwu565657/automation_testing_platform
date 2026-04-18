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
        GherkinKeyword keyword,
        String stepText,           // "I enter \"admin\" into the \"username\" field"
        ActionType actionType,     // CLICK, TYPE_TEXT, HTTP_REQUEST
        Map<String, String> actionParameters,
        String targetElementId,    // nullable — reference to PageElement
        boolean background,
        boolean continueOnFailure
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
