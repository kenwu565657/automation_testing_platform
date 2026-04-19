CREATE TABLE IF NOT EXISTS environments (
    id         VARCHAR(36)  PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    base_url   VARCHAR(500),
    project_id VARCHAR(36)  NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    variables  JSONB        NOT NULL DEFAULT '{}',
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW()
    );

CREATE INDEX idx_environments_project ON environments(project_id);