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
        languageVersion = JavaLanguageVersion.of(21)
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

    // 如果子模块已经应用了 java 或 kotlin 插件，我们可以定义全局依赖
    // 注意：确保子模块的 build.gradle.kts 中应用了 `java-library` 或 `kotlin("jvm")`
    apply(plugin = "java-library") // 可选：如果您希望所有子模块默认都是 Java 库

    dependencies {
        // 【核心修改】在这里全局定义 Spring Boot 版本
        // 使用 platform 引入 BOM，子模块中的 dependencies 就不再需要写版本号了
        implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.2"))

        // 如果您也想统一管理 Spring Cloud (使用您定义的 springCloudVersion)
        val springCloudVersion: String by rootProject.extra
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
    }

    tasks.register("prepareKotlinBuildScriptModel") {
        // no-op
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
