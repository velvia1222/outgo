package com.outgo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.outgo.model.User;

@ConfigAutowireable
@Dao
public interface UserDao {
    @Select
    User selectByUsername(String username);
}
