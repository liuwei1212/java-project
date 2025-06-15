package com.campus.announcement.mapper;

import com.campus.announcement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    User findById(@Param("id") Long id);
    int insertUser(User user);
    int updateUser(User user);
    List<User> findAll();
    int deleteUser(@Param("id") Long id);
} 