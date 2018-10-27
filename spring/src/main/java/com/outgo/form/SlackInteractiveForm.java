package com.outgo.form;

import java.util.List;

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
public class SlackInteractiveForm extends AbstractSlackForm {
    private String token;
    private String type;
    private Team team;
    private Channel channel;
    private User user;
    private List<Action> actions;

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Team {
        private String domain;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Channel {
        private String name;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String name;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Action {
        private String name;
        private String type;
        private String value;
        private List<Option> selected_options;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Option {
        private String value;
    }

    public boolean hasError() {
        if (!SLACK_TOKEN_VERIFICATION.equals(token)) {
            log.error("Invalid Token:{}", toString());
            return true;
        }
        if (!"interactive_message".equals(type)) {
            log.error("Invalid Type:{}", toString());
            return true;
        }
        if (!"n0712".equals(team.getDomain())) {
            log.error("Invalid Team:{}", toString());
            return true;
        }
        if (!"outgo".equals(channel.getName())) {
            log.error("Invalid Channel:{}", toString());
            return true;
        }
        try {
            Buyer.of(user.getName());
        } catch (IllegalArgumentException e) {
            log.debug("Invalid User:{}", toString());
            return true;
        }
        return false;
    }

    public Buyer getBuyer() {
        return Buyer.of(user.getName());
    }

    public void updateOutgoBot(OutgoBot outgoBot) {
        if (actions == null) {
            return;
        }
        Action action = actions.get(0);
        if (action == null) {
            return;
        }
        if ("category_list".equals(action.getName())) {
            List<Option> options = action.getSelected_options();
            if (options != null) {
                Option option = options.get(0);
                if (option != null) {
                    String category = option.getValue();
                    if (category != null && !category.isEmpty()) {
                        outgoBot.setCategory(category);
                    }
                }
            }
        } else if ("confirm".equals(action.getName())) {
            if ("OK".equals(action.getValue())) {
                outgoBot.setConfirmed(true);
            } else {
                outgoBot.clear();
            }
        }
    }
}
