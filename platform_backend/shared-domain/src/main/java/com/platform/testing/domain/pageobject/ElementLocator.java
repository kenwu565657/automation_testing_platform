package com.platform.testing.domain.pageobject;

import com.platform.testing.domain.common.ValueObject;
import com.platform.testing.domain.constant.LocatorStrategy;
import com.platform.testing.domain.constant.PlatformType;

import java.util.Objects;

public record ElementLocator(
        PlatformType platformType,
        LocatorStrategy locatorStrategy,
        String locatorValue,
        LocatorStrategy fallbackStrategy,
        String fallbackValue
) implements ValueObject {
    public ElementLocator {
        Objects.requireNonNull(platformType, "platformType is required");
        Objects.requireNonNull(locatorStrategy, "locatorStrategy is required");
        Objects.requireNonNull(locatorValue, "locatorValue is required");
    }
}
