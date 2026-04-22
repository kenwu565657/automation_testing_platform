package com.platform.testing.domain.execution;

import com.platform.testing.domain.constant.BrowserType;
import com.platform.testing.domain.constant.OSType;
import com.platform.testing.domain.constant.PlatformType;

import java.util.Map;
import java.util.Objects;

/**
 * DDD Value Object: describes WHERE a test runs.
 * Immutable — if you change the browser, you create a new target.
 */
public record ExecutionTarget(
        String id,
        String name,
        PlatformType platformType,
        BrowserType browserType,
        String browserVersion,
        boolean headless,
        OSType osType,
        String osVersion,
        Integer viewportWidth,
        Integer viewportHeight,
        String deviceName,
        String platformVersion,
        String appPackage,
        String bundleId,
        String appPath,
        String remoteUrl,
        Map<String, String> extraCapabilities
) {
    public ExecutionTarget {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(platformType);
        if (extraCapabilities == null) {
            extraCapabilities = Map.of();
        }
    }
}
