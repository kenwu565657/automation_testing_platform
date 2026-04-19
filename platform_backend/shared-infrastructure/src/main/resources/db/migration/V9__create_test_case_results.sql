CREATE TABLE IF NOT EXISTS test_case_results (
    id                  VARCHAR(36) PRIMARY KEY,
    test_run_id         VARCHAR(36) NOT NULL REFERENCES test_runs(id) ON DELETE CASCADE,
    test_case_id        VARCHAR(36) NOT NULL REFERENCES test_cases(id),
    execution_target_id VARCHAR(36) NOT NULL REFERENCES execution_targets(id),
    status              VARCHAR(50) NOT NULL DEFAULT 'QUEUED',
    started_at          TIMESTAMP   NOT NULL DEFAULT NOW(),
    completed_at        TIMESTAMP,
    duration_ms         BIGINT      NOT NULL DEFAULT 0,
    error_message       TEXT,
    stack_trace         TEXT,
    runtime_variables   JSONB       DEFAULT '{}'
    );

CREATE INDEX idx_case_results_run    ON test_case_results(test_run_id);
CREATE INDEX idx_case_results_case   ON test_case_results(test_case_id);
CREATE INDEX idx_case_results_status ON test_case_results(status);