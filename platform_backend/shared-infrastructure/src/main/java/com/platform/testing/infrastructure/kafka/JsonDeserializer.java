package com.platform.testing.infrastructure.kafka;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.testing.infrastructure.json.ObjectMapperFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Generic Kafka JSON Deserializer using the shared ObjectMapper.
 *
 * Configure the target type via Kafka consumer properties:
 *   props.put("json.deserializer.type", "com.platform.event.result.TestCaseCompletedEvent");
 *
 * Or in Spring Boot:
 *   spring.kafka.consumer.properties.json.deserializer.type=com.platform.event.result.TestCaseCompletedEvent
 *
 * If no type is configured, deserializes to a generic Map.
 *
 * @param <T> The type to deserialize into
 */
public class JsonDeserializer<T> implements Deserializer<T> {

    private static final Logger log = LoggerFactory.getLogger(JsonDeserializer.class);
    private static final String TYPE_CONFIG = "json.deserializer.type";

    private final ObjectMapper mapper = ObjectMapperFactory.getInstance();
    private JavaType targetType;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Object typeValue = configs.get(TYPE_CONFIG);
        if (typeValue != null) {
            try {
                Class<?> clazz = Class.forName(typeValue.toString());
                this.targetType = mapper.getTypeFactory().constructType(clazz);
            } catch (ClassNotFoundException e) {
                throw new SerializationException(
                        "Failed to resolve deserializer type: " + typeValue, e);
            }
        } else {
            // Default: deserialize to Map
            this.targetType = mapper.getTypeFactory()
                    .constructMapType(java.util.LinkedHashMap.class, String.class, Object.class);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            return (T) mapper.readValue(data, targetType);
        } catch (Exception e) {
            String msg = String.format("Error deserializing message from topic '%s': %s", topic, e.getMessage());
            log.error(msg, e);
            throw new SerializationException(msg, e);
        }
    }
}
