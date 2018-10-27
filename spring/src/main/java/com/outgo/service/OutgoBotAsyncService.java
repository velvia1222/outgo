package com.outgo.service;

import com.outgo.form.SlackForm;
import com.outgo.model.OutgoBot;
import com.outgo.repository.OutgoBotRepository;
import com.outgo.repository.OutgoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OutgoBotAsyncService {
    @Autowired
    private OutgoBotRepository outgoBotRepository;
    @Autowired
    private OutgoRepository outgoRepository;

    @Async
    public void reply(SlackForm form) {
        OutgoBot outgoBot = outgoBotRepository.find(form.getBuyer());
        form.updateOutgoBot(outgoBot);
        if (outgoBot.isConfirmed()) {
            outgoRepository.save(outgoBot.toOutgo());
        }
        outgoBot.reply();
        outgoBotRepository.save(outgoBot);
    }
}
