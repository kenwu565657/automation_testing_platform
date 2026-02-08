extra["springCloudVersion"] = "2025.1.0"

plugins {
    java
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.spring.dependency.management) apply false
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "platform_backend"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common_module"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

subprojects {
    repositories {
        mavenCentral()
    }

    tasks.register("prepareKotlinBuildScriptModel") {
        // no-op
    }
}

val springCloudVersion: String by project.extra

tasks.withType<Test> {
    useJUnitPlatform()
}
