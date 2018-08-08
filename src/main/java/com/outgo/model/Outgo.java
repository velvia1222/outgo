package com.outgo.model;

import lombok.Data;

import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "outgo")
@Data
public class Outgo {
    private Timestamp created_at;
    private Timestamp modified_at;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status;
    private String buyer;
    private long amount;
    private String category;
}
