package com.outgo.form;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outgo.config.ConstConfig.SlackToken;
import com.outgo.config.ConstConfig.SlackUserId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractSlackForm implements SlackForm {
    static String SLACK_TOKEN_VERIFICATION;
    static String SLACK_USERID_N;
    static String SLACK_USERID_Y;

    @Autowired
    private SlackToken slackToken;
    @Autowired
    private SlackUserId slackUserId;

    @PostConstruct
    public void postConstruct() {
        SLACK_TOKEN_VERIFICATION = slackToken.getVerification();
        SLACK_USERID_N = slackUserId.getN();
        SLACK_USERID_Y = slackUserId.getY();
    }
}
