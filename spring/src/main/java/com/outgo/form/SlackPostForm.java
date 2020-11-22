package com.outgo.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SlackPostForm {
    private String token;
    private String channel;
    private String text;
    private String response_type;
    private List<Attachment> attachments;

    @Data
    public static class Attachment {
        private String text;
        private String fallback;
        private String callback_id;
        private String color;
        private String attachment_type;
        private List<Action> actions;
    }

    @Data
    public static class Action {
        private String name;
        private String text;
        private String type;
        private String value;
        private String style;
        private List<Option> options;
    }

    @Data
    public static class Option {
        String text;
        String value;
    }

    public static List<Option> buildOptions(String... optionStrs) {
        List<Option> options = new ArrayList<>();
        for (String optionStr : optionStrs) {
            Option option = new Option();
            option.setText(optionStr);
            option.setValue(optionStr);
            options.add(option);
        }
        return options;
    }
}
