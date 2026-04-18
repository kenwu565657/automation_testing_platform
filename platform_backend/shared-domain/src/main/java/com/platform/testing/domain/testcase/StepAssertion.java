package com.platform.testing.domain.testcase;

import com.platform.testing.domain.common.ValueObject;
import com.platform.testing.domain.constant.AssertionType;
import com.platform.testing.domain.constant.ComparisonOperator;

public record StepAssertion(
        AssertionType assertionType,
        ComparisonOperator operator,
        String expectedValue,
        String actualValueSource
) implements ValueObject {
}
