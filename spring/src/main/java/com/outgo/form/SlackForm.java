package com.outgo.form;

import com.outgo.domain.Buyer;
import com.outgo.model.OutgoBot;

public interface SlackForm {
    public Buyer getBuyer();
    public void updateOutgoBot(OutgoBot outgoBot);
}
