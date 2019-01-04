package com.outgo.repository;

import com.outgo.dao.CompletedMessageDao;
import com.outgo.model.CompletedMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompletedMessageRepository {

    @Autowired
    private CompletedMessageDao completedMessageDao;

    public CompletedMessages findAll() {
        return new CompletedMessages(completedMessageDao.selectAll());
    }
}
