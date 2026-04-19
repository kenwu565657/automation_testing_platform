CREATE TABLE IF NOT EXISTS page_objects (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    page_url    VARCHAR(500),
    project_id  VARCHAR(36)  NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS page_elements (
    id              VARCHAR(36)  PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    page_object_id  VARCHAR(36)  NOT NULL REFERENCES page_objects(id) ON DELETE CASCADE,
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT NOW(),

    UNIQUE (page_object_id, name)
    );

CREATE TABLE IF NOT EXISTS element_locators (
    id                VARCHAR(36)  PRIMARY KEY,
    page_element_id   VARCHAR(36)  NOT NULL REFERENCES page_elements(id) ON DELETE CASCADE,
    platform_type     VARCHAR(50)  NOT NULL,
    locator_strategy  VARCHAR(50)  NOT NULL,
    locator_value     TEXT         NOT NULL,
    fallback_strategy VARCHAR(50),
    fallback_value    TEXT,

    UNIQUE (page_element_id, platform_type)
    );

CREATE INDEX idx_page_objects_project  ON page_objects(project_id);
CREATE INDEX idx_page_elements_object  ON page_elements(page_object_id);
CREATE INDEX idx_element_locators_elem ON element_locators(page_element_id);