package com.outgo.model;

import java.util.List;

public class Outgoes {
    private List<Outgo> outgoList;

    public Outgoes(List<Outgo> outgoList) {
        this.outgoList = outgoList;
    }

    public List<Outgo> asList() {
        return outgoList;
    }
}
