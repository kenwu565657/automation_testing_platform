CREATE TABLE IF NOT EXISTS test_cases (
    id              VARCHAR(36)  PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    feature_title   VARCHAR(500) NOT NULL,
    scenario_title  VARCHAR(500) NOT NULL,
    test_type       VARCHAR(50)  NOT NULL,
    priority        VARCHAR(50)  NOT NULL DEFAULT 'MEDIUM',
    test_suite_id   VARCHAR(36)  REFERENCES test_suites(id) ON DELETE SET NULL,
    active          BOOLEAN      NOT NULL DEFAULT TRUE,
    timeout_seconds INTEGER      NOT NULL DEFAULT 60,
    retry_count     INTEGER      NOT NULL DEFAULT 0,
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
    created_by      VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS test_case_tags (
    test_case_id VARCHAR(36)  NOT NULL REFERENCES test_cases(id) ON DELETE CASCADE,
    tag          VARCHAR(100) NOT NULL,
    PRIMARY KEY (test_case_id, tag)
    );

CREATE INDEX idx_test_cases_suite ON test_cases(test_suite_id);
CREATE INDEX idx_test_cases_type  ON test_cases(test_type);
CREATE INDEX idx_test_case_tags   ON test_case_tags(tag);