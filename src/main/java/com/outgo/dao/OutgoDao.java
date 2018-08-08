package com.outgo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

import com.outgo.model.Outgo;

@ConfigAutowireable
@Dao
public interface OutgoDao {
    @Select
    List<Outgo> selectNonProcessing();

    @Insert
    int insert(Outgo outgo);

    @Update
    int update(Outgo outgo);

    @Delete
    int delete(Outgo outgo);
}
