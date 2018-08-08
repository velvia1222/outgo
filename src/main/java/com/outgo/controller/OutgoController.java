package com.outgo.controller;

import com.outgo.resource.OutgoResource;
import com.outgo.service.OutgoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutgoController {

    @Autowired
    private OutgoService outgoService;

    @RequestMapping(path = "/api/outgoes", method = RequestMethod.GET)
    public List<OutgoResource> list() {
        return outgoService.list();
    }
}
