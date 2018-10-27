package com.outgo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import com.outgo.domain.Buyer;
import com.outgo.domain.Status;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "outgo")
@Data
@NoArgsConstructor
public class Outgo {

    private Timestamp created_at;
    private Timestamp modified_at;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Status status;
    private Buyer buyer;
    private long amount;
    private String category;

    public Outgo(
            long id,
            Status status,
            Buyer buyer,
            long amount,
            String category) {
        this.id = id;
        this.status = status;
        this.buyer = buyer;
        this.amount = amount;
        this.category = category;
    }
}
