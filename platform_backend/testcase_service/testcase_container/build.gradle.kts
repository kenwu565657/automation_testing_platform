plugins {
    `java-library`
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "testcase_container"

dependencies {
    api(project(":testcase_service:testcase_domain"))
    api(project(":testcase_service:testcase_persistence"))
    api(project(":testcase_service:testcase_web"))

    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.web)
    runtimeOnly(libs.postgresql)
    implementation(libs.aws.secretsmanager)

    implementation(libs.jackson.databind)

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
