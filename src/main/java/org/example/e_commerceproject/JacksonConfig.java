package org.example.e_commerceproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Set the nesting depth limits
        objectMapper.getFactory().setStreamReadConstraints(
                StreamReadConstraints.builder().maxNestingDepth(2000).build()
        );
        objectMapper.getFactory().setStreamWriteConstraints(
                StreamWriteConstraints.builder().maxNestingDepth(2000).build()
        );
        objectMapper.registerModule(new JavaTimeModule());

        // Prevent timestamps from being serialized as epoch timestamps
        objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Additional settings (if needed)
        objectMapper.enable(SerializationFeature.FAIL_ON_SELF_REFERENCES);

        return objectMapper;
    }
}
