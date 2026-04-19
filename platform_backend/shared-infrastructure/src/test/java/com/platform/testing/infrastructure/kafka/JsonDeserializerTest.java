package com.platform.testing.infrastructure.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    private JsonDeserializer<Object> deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new JsonDeserializer<>();
    }

    @Test
    void testDeserializeSuccessWithConfiguredType() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("json.deserializer.type", TestObject.class.getName());
        deserializer.configure(configs, false);

        byte[] data = "{\"name\":\"test-name\",\"value\":42}".getBytes(StandardCharsets.UTF_8);
        Object result = deserializer.deserialize("test-topic", data);

        assertInstanceOf(TestObject.class, result);
        TestObject testObj = (TestObject) result;
        assertEquals("test-name", testObj.getName());
        assertEquals(42, testObj.getValue());
    }

    @Test
    void testDeserializeSuccessWithDefaultMap() {
        // No type configured, should default to Map
        deserializer.configure(new HashMap<>(), false);

        byte[] data = "{\"name\":\"test-name\",\"value\":42}".getBytes(StandardCharsets.UTF_8);
        Object result = deserializer.deserialize("test-topic", data);

        assertInstanceOf(Map.class, result);
        Map<?, ?> map = (Map<?, ?>) result;
        assertEquals("test-name", map.get("name"));
        assertEquals(42, map.get("value"));
    }

    @Test
    void testDeserializeNullOrEmpty() {
        assertNull(deserializer.deserialize("test-topic", null));
        assertNull(deserializer.deserialize("test-topic", new byte[0]));
    }

    @Test
    void testConfigureClassNotFound() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("json.deserializer.type", "com.invalid.UnknownClass");

        SerializationException e = assertThrows(SerializationException.class, () -> deserializer.configure(configs, false));

        assertTrue(e.getMessage().contains("Failed to resolve deserializer type"));
    }

    @Test
    void testDeserializeException() {
        deserializer.configure(new HashMap<>(), false);
        byte[] invalidData = "invalid-json-data".getBytes(StandardCharsets.UTF_8);

        SerializationException e = assertThrows(SerializationException.class, () -> deserializer.deserialize("test-topic", invalidData));

        assertTrue(e.getMessage().contains("Error deserializing message"));
    }

    static class TestObject {
        private String name;
        private int value;

        public TestObject() {} // Jackson needs empty constructor

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

