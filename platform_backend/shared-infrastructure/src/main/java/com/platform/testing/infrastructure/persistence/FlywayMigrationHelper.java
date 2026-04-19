package com.platform.testing.infrastructure.persistence;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Flyway migration helper for non-Spring services (Vert.x, Ktor).
 * Spring Boot's admin-service uses Spring's auto-configured Flyway.
 * Engine (Vert.x) and Report (Ktor) call this manually at startup.
 * The SQL scripts live in shared-infra resources: db/migration/
 * Usage:
 *   FlywayMigrationHelper.migrate("jdbc:postgresql://localhost:5432/testplatform", "platform", "platform123");
 */
public final class FlywayMigrationHelper {

    private static final Logger log = LoggerFactory.getLogger(FlywayMigrationHelper.class);

    private FlywayMigrationHelper() {}

    public static void migrate(String jdbcUrl, String username, String password) {
        log.info("Running Flyway migration against: {}", jdbcUrl);

        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        var result = flyway.migrate();
        log.info("Flyway migration complete: {} migrations applied", result.migrationsExecuted);
    }

    public static void migrate(String jdbcUrl, String username, String password, String schema) {
        log.info("Running Flyway migration against: {} schema: {}", jdbcUrl, schema);

        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .schemas(schema)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        var result = flyway.migrate();
        log.info("Flyway migration complete: {} migrations applied", result.migrationsExecuted);
    }
}
