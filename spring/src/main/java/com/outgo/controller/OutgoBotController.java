package com.outgo.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.outgo.form.SlackEventForm;
import com.outgo.form.SlackInteractiveForm;
import com.outgo.service.OutgoBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OutgoBotController {

    @Autowired
    private OutgoBotService outgoBotService;

    @RequestMapping(
            path = "/api/outgobot/event",
            method = RequestMethod.POST)
    public String handleEvent(@RequestBody SlackEventForm form) {
        return outgoBotService.replyToEvent(form);
    }

    @RequestMapping(
            path = "/api/outgobot/interactive",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void handleInteractive(@RequestParam("payload") String payload) {
        SlackInteractiveForm form = null;
        try {
            form = new ObjectMapper().readValue(payload, SlackInteractiveForm.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            return;
        }
        outgoBotService.replyToInteractive(form);
    }
}
