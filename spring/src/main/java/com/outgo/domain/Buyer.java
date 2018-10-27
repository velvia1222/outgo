package com.outgo.domain;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public enum Buyer {
    N("n"),
    Y("y");

    private String value;
    private Buyer(String value) {
        this.value = value;
    }
    public static Buyer of(String value) {
        for (Buyer buyer : Buyer.values()) {
            if (buyer.value.equals(value)) {
                return buyer;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return this.value;
    }
}
