package com.campus.announcement.service;

import com.campus.announcement.model.User;
import java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    User findById(Long id);
    boolean updateUser(User user);
    List<User> findAll();
    boolean addUser(User user);
    boolean deleteUser(Long id);
} 