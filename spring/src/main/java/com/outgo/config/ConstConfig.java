package com.outgo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
public class ConstConfig {
    @Component
    @ConfigurationProperties(prefix = "slack.token")
    @Data
    public static class SlackToken {
        private String verification;
        private String botAccess;
    }

    @Component
    @ConfigurationProperties(prefix = "slack.url")
    @Data
    public static class SlackUrl {
        private String postMessage;
    }

    @Component
    @ConfigurationProperties(prefix = "slack.userId")
    @Data
    public static class SlackUserId {
        private String n;
        private String y;
    }
}
