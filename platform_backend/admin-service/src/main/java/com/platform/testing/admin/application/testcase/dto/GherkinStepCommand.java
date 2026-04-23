package com.platform.testing.admin.application.testcase.dto;

import com.platform.testing.domain.constant.ActionType;
import com.platform.testing.domain.constant.AssertionType;
import com.platform.testing.domain.constant.ComparisonOperator;
import com.platform.testing.domain.constant.ExtractionSource;
import com.platform.testing.domain.constant.GherkinKeyword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record GherkinStepCommand(
        int orderIndex,
        String name,
        @NotNull GherkinKeyword keyword,
        @NotBlank String stepText,
        @NotNull ActionType actionType,
        Map<String, String> actionParameters,
        String targetElementId,
        // assertion (optional)
        AssertionType assertionType,
        ComparisonOperator assertionOperator,
        String expectedValue,
        String actualValueSource,
        // variable extraction (optional)
        String extractionVarName,
        ExtractionSource extractionSource,
        String extractionExpression,
        boolean background,
        boolean continueOnFailure,
        int waitBeforeMs,
        int waitAfterMs
) {}
