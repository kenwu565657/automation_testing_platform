CREATE TABLE IF NOT EXISTS test_step_results (
    id                  VARCHAR(36) PRIMARY KEY,
    test_case_result_id VARCHAR(36) NOT NULL REFERENCES test_case_results(id) ON DELETE CASCADE,
    test_step_id        VARCHAR(36),
    order_index         INTEGER     NOT NULL,
    status              VARCHAR(50) NOT NULL,
    duration_ms         BIGINT      NOT NULL DEFAULT 0,
    actual_value        TEXT,
    expected_value      TEXT,
    error_message       TEXT,
    screenshot_path     VARCHAR(500),
    metadata            JSONB       DEFAULT '{}'
    );

CREATE INDEX idx_step_results_case ON test_step_results(test_case_result_id);
CREATE INDEX idx_step_results_status ON test_step_results(status);