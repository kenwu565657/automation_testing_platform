package com.platform.testing.domain.testcase;

import com.platform.testing.domain.common.ValueObject;
import com.platform.testing.domain.constant.ExtractionSource;

public record VariableExtraction(
        String variableName,
        ExtractionSource source,
        String extractionExpression
) implements ValueObject {
}
