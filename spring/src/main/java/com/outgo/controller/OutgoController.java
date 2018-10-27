package com.outgo.controller;

import com.outgo.form.OutgoIdsForm;
import com.outgo.form.OutgoForm;
import com.outgo.service.OutgoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutgoController {

    @Autowired
    private OutgoService outgoService;

    @RequestMapping(path = "/api/outgoes", method = RequestMethod.GET)
    public List<OutgoForm> get() {
        return outgoService.findNonProcessing();
    }

    @RequestMapping(path = "/api/outgoes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody OutgoForm outgoForm) {
        outgoService.register(outgoForm);
    }

    @RequestMapping(path = "/api/outgoes", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@RequestBody OutgoForm outgoForm) {
        outgoService.update(outgoForm);
    }

    @RequestMapping(path = "/api/outgoes/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody OutgoIdsForm outgoIdsForm) {
        outgoService.delete(outgoIdsForm);
    }

    @RequestMapping(path = "/api/outgoes/pay", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pay(@RequestBody OutgoIdsForm outgoIdsForm) {
        outgoService.pay(outgoIdsForm);
    }
}
