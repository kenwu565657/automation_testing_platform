plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "testcase_service"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
