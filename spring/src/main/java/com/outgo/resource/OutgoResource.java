package com.outgo.resource;

import com.outgo.domain.Status;
import com.outgo.model.Outgo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutgoResource {
    private long id;
    private String status;
    private String buyer;
    private long amount;
    private String category;

    public OutgoResource(Outgo outgo) {
        id = outgo.getId();
        status = outgo.getStatus().getValue();
        buyer = outgo.getBuyer();
        amount = outgo.getAmount();
        category = outgo.getCategory();
    }

    public Outgo toOutgo() {
        return new Outgo(
                id,
                Status.of(status),
                buyer,
                amount,
                category);
    }
}
