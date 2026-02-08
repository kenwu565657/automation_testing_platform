plugins {
    java
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "testcase_domain"

dependencies {
    api(project(":common_module"))
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)
}