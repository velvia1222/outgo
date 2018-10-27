package com.outgo.model;

import java.net.URI;

import com.outgo.form.SlackPostForm;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SlackBot {
    String slackTokenBotAccess;
    String slackUrlPostMessage;

    void postMessage(String channel, String message) {
        SlackPostForm slackPostForm = new SlackPostForm();
        slackPostForm.setChannel(channel);
        slackPostForm.setText(message);
        RequestEntity<SlackPostForm> req = RequestEntity
                .post(URI.create(slackUrlPostMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + slackTokenBotAccess)
                .body(slackPostForm);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(req, SlackPostForm.class);
    }
}
