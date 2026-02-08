plugins {
    `java-library`
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "testcase_persistence"

dependencies {
    api(project(":testcase_service:testcase_domain"))
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)

    implementation(libs.mapstruct)
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)
    annotationProcessor(libs.mapstruct.processor)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}
