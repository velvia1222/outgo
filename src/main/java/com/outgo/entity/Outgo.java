package com.outgo.entity;

import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Outgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long number;
    public String status;
    public String buyer;
    public long amount;
    public String category;
}
