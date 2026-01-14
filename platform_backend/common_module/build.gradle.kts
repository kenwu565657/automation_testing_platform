plugins {
    java
    kotlin("jvm")
    id("io.freefair.lombok") version "8.6"
}

group = "com.platform.testing"
version = "0.0.1-SNAPSHOT"
description = "common_module"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}