package com.outgo.dao;

import java.util.List;

import com.outgo.model.CompletedMessage;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface CompletedMessageDao {
    @Select
    List<CompletedMessage> selectAll();
}
