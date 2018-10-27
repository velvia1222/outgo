package com.outgo.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.outgo.domain.Buyer;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@SuppressWarnings("serial")
@Data
public class OutgoBotBeans implements Serializable {
    private Map<Buyer, OutgoBotBean> outgoBotBeans = new HashMap<>();

    @Data
    @AllArgsConstructor
    public static class OutgoBotBean {
        long amount;
        String category;
    }

    public OutgoBotBean find(Buyer buyer) {
        return outgoBotBeans.get(buyer);
    }

    public void save(Buyer buyer, OutgoBotBean outgoBotBean) {
        outgoBotBeans.put(buyer, outgoBotBean);
    }
}
