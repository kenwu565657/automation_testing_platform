package com.platform.testing.event.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

/**
 * Standard envelope wrapping every Kafka message.
 *
 * Kafka message value = JSON of DomainEventEnvelope<SomeEvent>
 *
 * This gives every event a consistent structure:
 * {
 *   "metadata": { "eventId": "...", "eventType": "...", ... },
 *   "payload": { ... actual event data ... }
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DomainEventEnvelope<T>(
        EventMetadata metadata,
        T payload
) {
    public DomainEventEnvelope {
        Objects.requireNonNull(metadata, "metadata is required");
        Objects.requireNonNull(payload, "payload is required");
    }

    public static <T> DomainEventEnvelope<T> wrap(T payload, String eventType, String sourceService) {
        return new DomainEventEnvelope<>(
                EventMetadata.create(eventType, sourceService),
                payload
        );
    }

    public static <T> DomainEventEnvelope<T> wrap(T payload, String eventType,
                                                  String sourceService, String correlationId) {
        return new DomainEventEnvelope<>(
                EventMetadata.create(eventType, sourceService, correlationId),
                payload
        );
    }
}
