plugins {
    java
    `java-library`
}

dependencies {
    api(libs.jakarta.persistence.api)
    api(libs.jakarta.validation.api)

    // Jackson (runtime — this module provides actual serializers)
    api(libs.jackson.databind)
    api(libs.jackson.annotations)
    api(libs.jackson.datatype.jsr310)
}