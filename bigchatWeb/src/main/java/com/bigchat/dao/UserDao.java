package com.bigchat.dao;

import com.bigchat.domain.User;

/**
 * Created by wang on 16-11-26.
 */
public interface UserDao {
    public User loginDao(User user);
    public boolean registerDao(User user);
    public User findUserById(String userId);
}
