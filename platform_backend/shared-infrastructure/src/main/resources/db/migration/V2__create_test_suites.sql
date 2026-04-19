CREATE TABLE IF NOT EXISTS test_suites (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    project_id  VARCHAR(36)  NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    active      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS test_suite_test_cases (
    test_suite_id VARCHAR(36) NOT NULL REFERENCES test_suites(id) ON DELETE CASCADE,
    test_case_id  VARCHAR(36) NOT NULL,
    PRIMARY KEY (test_suite_id, test_case_id)
    );

CREATE INDEX idx_test_suites_project ON test_suites(project_id);