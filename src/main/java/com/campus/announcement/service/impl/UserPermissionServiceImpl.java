package com.campus.announcement.service.impl;
import com.campus.announcement.mapper.UserPermissionMapper;
import com.campus.announcement.model.UserPermission;
import com.campus.announcement.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    @Override
    public List<String> getPermissions(Long userId) {
        return userPermissionMapper.findByUserId(userId).stream().map(UserPermission::getPermission).collect(Collectors.toList());
    }
    @Override
    public boolean grantPermission(Long userId, String permission) {
        UserPermission up = new UserPermission();
        up.setUserId(userId);
        up.setPermission(permission);
        return userPermissionMapper.insert(up) > 0;
    }
    @Override
    public boolean revokePermission(Long userId, String permission) {
        return userPermissionMapper.deleteByUserIdAndPermission(userId, permission) > 0;
    }
} 