package com.wq.service;

import com.wq.domain.User;

import java.util.List;

public interface UserService {

    public User login(User user);

    void editPassword(String id, String password);

    void add(User model, String[] roleIds);

    List<User> findAll();
}
