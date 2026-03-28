plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.shadow)
}

application {
    mainClass.set("com.platform.testing.report.ReportApplicationKt")
}

dependencies {
    // ── Shared ──
    implementation(project(":shared-domain"))
    implementation(project(":shared-event"))

    // ── Ktor (via bundle) ──
    implementation(libs.bundles.ktor.server)
    implementation(libs.bundles.ktor.client)

    // ── Kotlin ──
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // ── Kafka ──
    implementation(libs.kafka.clients)

    // ── Exposed ORM (via bundle) ──
    implementation(libs.bundles.exposed)
    runtimeOnly(libs.postgresql)

    // ── Elasticsearch ──
    implementation(libs.elasticsearch.java)

    // ── Redis ──
    implementation(libs.lettuce)

    // ── Jackson (for Kafka deserialization) ──
    implementation(libs.jackson.module.kotlin)
    implementation(libs.jackson.datatype.jsr310)

    // ── Logging ──
    implementation(libs.logback.classic)

    // ── Testing ──
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.bundles.testcontainers)
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("fat")
    mergeServiceFiles()
    manifest { attributes["Main-Class"] = application.mainClass.get() }
}