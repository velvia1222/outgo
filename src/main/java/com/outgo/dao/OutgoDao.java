package com.outgo.dao;

import com.outgo.entity.Outgo;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface OutgoDao {
    @Select
    List<Outgo> selectAll();

    @Insert
    @Transactional
    int insert(Outgo outgo);
}
