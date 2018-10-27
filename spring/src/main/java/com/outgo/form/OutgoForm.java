package com.outgo.form;

import java.text.SimpleDateFormat;

import com.outgo.domain.Buyer;
import com.outgo.domain.Status;
import com.outgo.model.Outgo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutgoForm {
    private String created_at;
    private long id;
    private String status;
    private String buyer;
    private long amount;
    private String category;

    public OutgoForm(Outgo outgo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        created_at = sdf.format(outgo.getCreated_at());
        id = outgo.getId();
        status = outgo.getStatus().getValue();
        buyer = outgo.getBuyer().getValue();
        amount = outgo.getAmount();
        category = outgo.getCategory();
    }

    public Outgo toOutgo() {
        return new Outgo(
                id,
                Status.of(status),
                Buyer.of(buyer),
                amount,
                category);
    }
}
