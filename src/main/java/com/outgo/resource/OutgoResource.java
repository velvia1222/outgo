package com.outgo.resource;

import com.outgo.model.Outgo;

import lombok.Getter;

@Getter
public class OutgoResource {
    private long id;
    private String status;
    private String buyer;
    private long amount;
    private String category;

    public OutgoResource(Outgo outgo) {
        id = outgo.getId();
        status = outgo.getStatus();
        buyer = outgo.getBuyer();
        amount = outgo.getAmount();
        category = outgo.getCategory();
    }
}
