package com.platform.testing.event.topic;

/**
 * Single source of truth for all Kafka topic names.
 * Referenced by every service — producers and consumers.
 */
public final class KafkaTopic {

    private KafkaTopic() {}

    // ── Admin → Engine ──
    public static final String TEST_EXECUTION_REQUEST = "test.execution.request";

    // ── Engine → Report ──
    public static final String TEST_RUN_STARTED       = "test.run.started";
    public static final String TEST_RUN_COMPLETED     = "test.run.completed";
    public static final String TEST_CASE_STARTED      = "test.case.started";
    public static final String TEST_CASE_COMPLETED    = "test.case.completed";
    public static final String TEST_STEP_COMPLETED    = "test.step.completed";

    // ── Admin → Anyone (lifecycle) ──
    public static final String TEST_CASE_CREATED      = "test.case.created";
    public static final String TEST_CASE_UPDATED      = "test.case.updated";
}
