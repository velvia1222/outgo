package com.outgo.domain;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public enum Status {
    NON_PROCESSING("0"),
    DONE("1");

    private String value;
    private Status(String value) {
        this.value = value;
    }
    public static Status of(String value) {
        for (Status status : Status.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return this.value;
    }
}
