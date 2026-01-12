extra["springCloudVersion"] = "2025.1.0"

plugins {
    java
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
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

subprojects {
    repositories {
        mavenCentral()
    }

    tasks.register("prepareKotlinBuildScriptModel") {
        // no-op
    }

    if (project.name != "common_module") {
        afterEvaluate {
            dependencies {
                implementation(project(":common_module"))
            }
        }
    }
}

val springCloudVersion: String by project.extra

tasks.withType<Test> {
    useJUnitPlatform()
}
