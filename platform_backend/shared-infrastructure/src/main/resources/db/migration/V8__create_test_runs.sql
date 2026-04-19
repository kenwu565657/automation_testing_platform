CREATE TABLE IF NOT EXISTS test_runs (
    id                  VARCHAR(36)  PRIMARY KEY,
    test_suite_id       VARCHAR(36)  NOT NULL REFERENCES test_suites(id),
    execution_target_id VARCHAR(36)  NOT NULL REFERENCES execution_targets(id),
    environment_id      VARCHAR(36)  NOT NULL REFERENCES environments(id),
    status              VARCHAR(50)  NOT NULL DEFAULT 'QUEUED',
    triggered_by        VARCHAR(255),
    started_at          TIMESTAMP    NOT NULL DEFAULT NOW(),
    completed_at        TIMESTAMP,
    duration_ms         BIGINT       NOT NULL DEFAULT 0,
    total_cases         INTEGER      NOT NULL DEFAULT 0,
    passed_cases        INTEGER      NOT NULL DEFAULT 0,
    failed_cases        INTEGER      NOT NULL DEFAULT 0,
    skipped_cases       INTEGER      NOT NULL DEFAULT 0
    );

CREATE INDEX idx_test_runs_suite  ON test_runs(test_suite_id);
CREATE INDEX idx_test_runs_status ON test_runs(status);
CREATE INDEX idx_test_runs_target ON test_runs(execution_target_id);