package com.outgo.repository;

import com.outgo.dao.UserDao;
import com.outgo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserDao userDao;

    public User findByUsername(String username) {
        return userDao.selectByUsername(username);
    }
}
