package com.outgo.repository;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.outgo.entity.User;

@ConfigAutowireable
@Dao
public interface UserRepository {
    @Select
    User selectByUsername(String username);
}
