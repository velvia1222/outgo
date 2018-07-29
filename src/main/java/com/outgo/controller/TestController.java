package com.outgo.controller;

import com.outgo.entity.Outgo;
import com.outgo.repository.OutgoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestController {

    @Autowired
    OutgoRepository outgoRepository;

    public List<Outgo> searchOutgo() {
        return outgoRepository.selectAll();
    }
}
