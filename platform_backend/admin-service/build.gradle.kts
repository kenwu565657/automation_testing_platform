plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    // ── Shared ──
    implementation(project(":shared-domain"))
    implementation(project(":shared-event"))

    // ── Spring Boot ──
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.kafka)

    // ── Database ──
    runtimeOnly(libs.postgresql)
    implementation(libs.bundles.flyway)

    // ── Utilities ──
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // ── Testing ──
    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly(libs.h2)
    testImplementation(libs.bundles.testcontainers)
}