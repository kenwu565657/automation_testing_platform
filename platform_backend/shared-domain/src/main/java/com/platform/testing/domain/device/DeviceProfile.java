package com.platform.testing.domain.device;

import com.platform.testing.domain.constant.BrowserType;
import com.platform.testing.domain.constant.OSType;
import com.platform.testing.domain.constant.PlatformType;

import java.util.Map;

public record DeviceProfile(
        String id,
        String name,
        PlatformType platform,
        OSType os,
        BrowserType browser,
        String browserVersion,
        String deviceName,
        String platformVersion,
        String automationName,
        String appPackage,
        String appActivity,
        String bundleId,
        String appPath,
        String gridUrl,
        String appiumUrl,
        boolean headless,
        String screenResolution,
        Map<String, Object> extraCapabilities
) {
}
