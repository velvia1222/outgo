package com.outgo.controller;

import com.outgo.dao.OutgoDao;
import com.outgo.entity.Outgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestController {

    @Autowired
    OutgoDao outgoDao;

    public List<Outgo> searchOutgo() {
        return outgoDao.selectAll();
    }
}
