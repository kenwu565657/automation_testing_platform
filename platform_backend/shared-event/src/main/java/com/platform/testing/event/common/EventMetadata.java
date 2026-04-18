package com.platform.testing.event.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Metadata attached to every domain event.
 * Enables tracing, ordering, and source identification.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EventMetadata(
        String eventId,
        String eventType,
        String sourceService,
        String correlationId,
        Instant timestamp
) {
    public EventMetadata {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(eventType);
        Objects.requireNonNull(sourceService);
        if (timestamp == null) timestamp = Instant.now();
        if (correlationId == null) correlationId = eventId;
    }

    public static EventMetadata create(String eventType, String sourceService, String correlationId) {
        return new EventMetadata(
                UUID.randomUUID().toString(),
                eventType,
                sourceService,
                correlationId,
                Instant.now()
        );
    }

    public static EventMetadata create(String eventType, String sourceService) {
        String id = UUID.randomUUID().toString();
        return new EventMetadata(id, eventType, sourceService, id, Instant.now());
    }
}
