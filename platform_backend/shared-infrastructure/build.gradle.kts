plugins {
    java
    `java-library`
}

dependencies {
    api(project(":shared-domain"))
    api(project(":shared-event"))

    // Jackson (runtime — this module provides actual serializers)
    api(libs.jackson.databind)
    api(libs.jackson.datatype.jsr310)

    // Kafka client (for serializer/deserializer classes)
    compileOnly(libs.kafka.clients)

    // Flyway (for migration helper)
    compileOnly(libs.flyway.core)
    compileOnly(libs.flyway.postgresql)

    // PostgresSQL driver
    compileOnly(libs.postgresql)

    // Logging
    implementation(libs.slf4j.api)

    implementation(libs.hibernate.core)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}