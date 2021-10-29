package com.broker.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Viktor Makarov
 */
@Configuration
@ConfigurationProperties(prefix = "broker")
@Getter
@Setter
public class MessageConfiguration {
    private String message;
}