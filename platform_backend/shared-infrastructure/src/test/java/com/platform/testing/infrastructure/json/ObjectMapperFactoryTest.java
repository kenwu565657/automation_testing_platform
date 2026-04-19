package com.platform.testing.infrastructure.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperFactoryTest {

    @Test
    void testGetInstanceReturnsSameInstance() {
        ObjectMapper instance1 = ObjectMapperFactory.getInstance();
        ObjectMapper instance2 = ObjectMapperFactory.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void testCreateReturnsNewInstance() {
        ObjectMapper instance1 = ObjectMapperFactory.create();
        ObjectMapper instance2 = ObjectMapperFactory.create();
        assertNotSame(instance1, instance2);
    }

    @Test
    void testJacksonConfiguration() throws Exception {
        ObjectMapper mapper = ObjectMapperFactory.getInstance();

        Instant timestamp = Instant.parse("2023-10-01T12:00:00Z");
        TestClass obj = new TestClass();
        obj.setTimestamp(timestamp);
        obj.setCamelCaseField("value");

        String json = mapper.writeValueAsString(obj);

        // Verify Instant is formatted as ISO-8601 string, not timestamp
        assertTrue(json.contains("\"2023-10-01T12:00:00Z\""));
        // Verify camelCase property exists
        assertTrue(json.contains("\"camelCaseField\":\"value\""));
        // Verify null fields are omitted
        assertFalse(json.contains("\"nullField\""));

        // Test unknown properties (FAIL_ON_UNKNOWN_PROPERTIES = false)
        String jsonWithUnknown = "{\"timestamp\":\"2023-10-01T12:00:00Z\",\"camelCaseField\":\"value\",\"unknownField\":\"unknown\"}";
        TestClass deserialized = mapper.readValue(jsonWithUnknown, TestClass.class);

        assertEquals(timestamp, deserialized.getTimestamp());
        assertEquals("value", deserialized.getCamelCaseField());
    }

    static class TestClass {
        private Instant timestamp;
        private String camelCaseField;
        private String nullField;

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public String getCamelCaseField() {
            return camelCaseField;
        }

        public void setCamelCaseField(String camelCaseField) {
            this.camelCaseField = camelCaseField;
        }

        public String getNullField() {
            return nullField;
        }

        public void setNullField(String nullField) {
            this.nullField = nullField;
        }
    }
}

