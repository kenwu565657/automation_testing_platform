plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "gateway"

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val springCloudVersion: String by rootProject.extra

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}
