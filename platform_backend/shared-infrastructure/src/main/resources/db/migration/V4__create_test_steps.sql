CREATE TABLE IF NOT EXISTS test_steps (
    id                    VARCHAR(36)  PRIMARY KEY,
    test_case_id          VARCHAR(36)  NOT NULL REFERENCES test_cases(id) ON DELETE CASCADE,
    order_index           INTEGER      NOT NULL,
    name                  VARCHAR(255) NOT NULL,
    gherkin_keyword       VARCHAR(10)  NOT NULL,
    gherkin_step_text     TEXT         NOT NULL,
    action_type           VARCHAR(50)  NOT NULL,
    action_parameters     JSONB        NOT NULL DEFAULT '{}',
    target_element_id     VARCHAR(36),
    assertion_type        VARCHAR(50),
    assertion_operator    VARCHAR(50),
    expected_value        TEXT,
    actual_value_source   TEXT,
    extraction_var_name   VARCHAR(255),
    extraction_source     VARCHAR(50),
    extraction_expression TEXT,
    is_background         BOOLEAN      NOT NULL DEFAULT FALSE,
    continue_on_failure   BOOLEAN      NOT NULL DEFAULT FALSE,
    wait_before_ms        INTEGER      NOT NULL DEFAULT 0,
    wait_after_ms         INTEGER      NOT NULL DEFAULT 0,

    UNIQUE (test_case_id, order_index)
    );

CREATE INDEX idx_test_steps_case ON test_steps(test_case_id);

CREATE TABLE IF NOT EXISTS test_parameters (
    id             VARCHAR(36)  PRIMARY KEY,
    test_case_id   VARCHAR(36)  NOT NULL REFERENCES test_cases(id) ON DELETE CASCADE,
    parameter_name VARCHAR(255) NOT NULL,
    data_rows      JSONB        NOT NULL DEFAULT '[]'
    );

CREATE INDEX idx_test_params_case ON test_parameters(test_case_id);