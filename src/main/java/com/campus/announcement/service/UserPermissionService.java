package com.campus.announcement.service;
import java.util.List;
public interface UserPermissionService {
    List<String> getPermissions(Long userId);
    boolean grantPermission(Long userId, String permission);
    boolean revokePermission(Long userId, String permission);
} 