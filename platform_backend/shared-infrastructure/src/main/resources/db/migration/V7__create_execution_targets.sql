CREATE TABLE IF NOT EXISTS execution_targets (
    id                 VARCHAR(36)  PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    description        TEXT,
    environment_id     VARCHAR(36)  NOT NULL REFERENCES environments(id) ON DELETE CASCADE,
    platform_type      VARCHAR(50)  NOT NULL,
    browser_type       VARCHAR(50),
    browser_version    VARCHAR(50),
    headless           BOOLEAN      NOT NULL DEFAULT FALSE,
    browser_arguments  TEXT,
    os_type            VARCHAR(50),
    os_version         VARCHAR(50),
    viewport_width     INTEGER,
    viewport_height    INTEGER,
    device_name        VARCHAR(255),
    device_udid        VARCHAR(255),
    platform_version   VARCHAR(50),
    automation_engine  VARCHAR(50),
    app_package        VARCHAR(255),
    app_activity       VARCHAR(255),
    bundle_id          VARCHAR(255),
    app_path           VARCHAR(500),
    remote_url         VARCHAR(500),
    extra_capabilities JSONB        NOT NULL DEFAULT '{}',
    active             BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at         TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at         TIMESTAMP    NOT NULL DEFAULT NOW()
    );

CREATE INDEX idx_exec_targets_env ON execution_targets(environment_id);
CREATE INDEX idx_exec_targets_platform ON execution_targets(platform_type);