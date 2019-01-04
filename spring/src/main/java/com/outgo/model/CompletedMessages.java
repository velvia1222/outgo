package com.outgo.model;

import java.util.List;
import java.util.Random;

public class CompletedMessages {
    private List<CompletedMessage> completedMessages;

    public CompletedMessages(List<CompletedMessage> completedMessages) {
        this.completedMessages = completedMessages;
    }

    public String getOneAtRandom() {
        return completedMessages.get(new Random().nextInt(completedMessages.size())).getMessage();
    }
}
