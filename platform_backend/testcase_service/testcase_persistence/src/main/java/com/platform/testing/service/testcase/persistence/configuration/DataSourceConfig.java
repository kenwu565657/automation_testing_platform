package com.platform.testing.service.testcase.persistence.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

// @Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws JsonProcessingException {
        DatabaseCredentials credentials = fetchCredentialsFromSecretsManager();

        String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s",
                credentials.getHost(),
                credentials.getPort(),
                credentials.getDbname());

        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(credentials.getUsername())
                .password(credentials.getPassword())
                .build();
    }

    private DatabaseCredentials fetchCredentialsFromSecretsManager() throws JsonProcessingException {
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();

        GetSecretValueResponse response = client.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId("rds/postgres/credentials")
                        .build()
        );

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.secretString(), DatabaseCredentials.class);
    }

    @Data
    private static class DatabaseCredentials {
        private String host;
        private int port = 5432;
        private String dbname;
        private String username;
        private String password;
    }
}
