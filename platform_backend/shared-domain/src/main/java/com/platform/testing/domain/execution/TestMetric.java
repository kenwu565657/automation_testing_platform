package com.platform.testing.domain.execution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Cross-boundary Value Object — computed metrics for a test execution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TestMetric(
        double passRate,
        double avgStepDuration,
        // Load-test specific
        Double throughput,
        Double p50ResponseTime,
        Double p95ResponseTime,
        Double p99ResponseTime,
        Double errorRate,
        Integer concurrentUsers
) {
    public static TestMetric simple(double passRate, double avgStepDuration) {
        return new TestMetric(passRate, avgStepDuration, null, null, null, null, null, null);
    }
}
