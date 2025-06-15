package com.campus.announcement.service.impl;

import com.campus.announcement.mapper.UserMapper;
import com.campus.announcement.model.User;
import com.campus.announcement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false;
        }
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteUser(id) > 0;
    }
} 