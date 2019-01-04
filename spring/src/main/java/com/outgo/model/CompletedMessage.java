package com.outgo.model;

import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "completed_message")
@Data
@NoArgsConstructor
public class CompletedMessage {

    private Timestamp created_at;
    private Timestamp modified_at;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
}
