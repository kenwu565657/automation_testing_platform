plugins {
    `java-library`
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "common_module"

dependencies {
    api(libs.lombok)
    annotationProcessor(libs.lombok)
}