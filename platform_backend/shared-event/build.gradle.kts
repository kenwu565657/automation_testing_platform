plugins {
    java
    `java-library`
}

dependencies {
    api(project(":shared-domain"))
    api(libs.jackson.annotations)
}