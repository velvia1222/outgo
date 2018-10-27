package com.outgo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outgo.domain.Buyer;
import com.outgo.model.OutgoBot;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackEventForm extends AbstractSlackForm {

    private String token;
    private String type;
    private String challenge;
    private Event event;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Event {
        private String type;
        private String user;
        private String text;
    }

    public boolean isUrlVerification() {
        return "url_verification".equals(type);
    }

    public boolean hasError() {
        if (!SLACK_TOKEN_VERIFICATION.equals(token)) {
            log.error("Invalid Token:{}", toString());
            return true;
        }
        if (!"event_callback".equals(type)) {
            log.error("Invalid Type:{}", toString());
            return true;
        }
        if (event == null) {
            log.error("Event is null:{}", toString());
            return true;
        }
        if (!SLACK_USERID_N.equals(event.getUser())
                && !SLACK_USERID_Y.equals(event.getUser())) {
            log.debug("Invalid User:{}", toString());
            return true;
        }
        if (!"message".equals(event.getType())) {
            log.error("Invalid Event Type:{}", toString());
            return true;
        }
        return false;
    }

    public Buyer getBuyer() {
        if (SLACK_USERID_Y.equals(event.getUser())) {
            return Buyer.Y;
        } else if (SLACK_USERID_N.equals(event.getUser())) {
            return Buyer.N;
        }
        throw new IllegalArgumentException(event.getUser());
    }

    public void updateOutgoBot(OutgoBot outgoBot) {
        if (outgoBot.getBuyer() == null) {
            outgoBot.setBuyer(getBuyer());
        }
        try {
            outgoBot.setAmount(Long.parseLong(event.getText()));
        } catch (NumberFormatException e) {
        }
    }
}
