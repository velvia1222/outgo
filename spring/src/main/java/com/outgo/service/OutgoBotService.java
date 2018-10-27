package com.outgo.service;

import com.outgo.form.SlackEventForm;
import com.outgo.form.SlackInteractiveForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutgoBotService {
    @Autowired
    private OutgoBotAsyncService outgoBotAsyncService;

    public String replyToEvent(SlackEventForm form) {
        if (form.isUrlVerification()) {
            return String.format("{\"challenge\":\"%s\"}", form.getChallenge());
        }
        if (form.hasError()) {
            return "{}";
        }

        outgoBotAsyncService.reply(form);
        return "{}";
    }

    public void replyToInteractive(SlackInteractiveForm form) {
        if (form.hasError()) {
            return;
        }

        outgoBotAsyncService.reply(form);
    }
}
