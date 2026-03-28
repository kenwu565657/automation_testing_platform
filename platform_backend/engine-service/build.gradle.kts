plugins {
    java
    application
    alias(libs.plugins.shadow)
}

application {
    mainClass.set("com.platform.engine.EngineMainVerticle")
}

dependencies {
    // ── Shared ──
    implementation(project(":shared-domain"))
    implementation(project(":shared-event"))

    // ── Vert.x ──
    implementation(libs.bundles.vertx.core)
    implementation(libs.bundles.vertx.kafka)
    implementation(libs.vertx.redis.client)
    implementation(libs.bundles.vertx.db)

    // ── Cucumber BDD ──
    implementation(libs.bundles.cucumber)

    // ── Selenium + Appium ──
    implementation(libs.selenium.java)
    implementation(libs.appium.java.client)

    // ── REST Assured (API test engine) ──
    implementation(libs.rest.assured)

    // ── Jackson ──
    implementation(libs.bundles.jackson)

    // ── Logging ──
    implementation(libs.logback.classic)

    // ── Testing ──
    testImplementation(libs.vertx.junit5)
    testImplementation(libs.junit.jupiter)
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("fat")
    mergeServiceFiles()
    manifest { attributes["Main-Class"] = application.mainClass.get() }
}