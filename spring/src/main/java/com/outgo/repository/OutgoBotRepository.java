package com.outgo.repository;

import com.outgo.bean.OutgoBotBeans;
import com.outgo.bean.OutgoBotBeans.OutgoBotBean;
import com.outgo.config.ConstConfig.SlackToken;
import com.outgo.config.ConstConfig.SlackUrl;
import com.outgo.domain.Buyer;
import com.outgo.model.OutgoBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutgoBotRepository {
    @Autowired
    private OutgoBotBeans outgoBotBeans;
    @Autowired
    private SlackToken slackToken;
    @Autowired
    private SlackUrl slackUrl;

    public OutgoBot find(Buyer buyer) {
        OutgoBotBean outgoBotBean = outgoBotBeans.find(buyer);
        if (outgoBotBean == null) {
            return new OutgoBot(
                    slackToken.getBotAccess(),
                    slackUrl.getPostMessage());
        }
        return new OutgoBot(
                slackToken.getBotAccess(),
                slackUrl.getPostMessage(),
                buyer,
                outgoBotBean.getAmount(),
                outgoBotBean.getCategory());
    }

    public void save(OutgoBot outgoBot) {
        Buyer buyer = outgoBot.getBuyer();
        OutgoBotBean outgoBotBean = outgoBotBeans.find(buyer);
        if (outgoBotBean == null) {
            outgoBotBeans.save(buyer, new OutgoBotBean(
                        outgoBot.getAmount(),
                        outgoBot.getCategory()));
        } else {
            outgoBotBean.setAmount(outgoBot.getAmount());
            outgoBotBean.setCategory(outgoBot.getCategory());
            outgoBotBeans.save(buyer, outgoBotBean);
        }
    }
}
