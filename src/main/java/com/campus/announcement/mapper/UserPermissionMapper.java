package com.campus.announcement.mapper;
import com.campus.announcement.model.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface UserPermissionMapper {
    List<UserPermission> findByUserId(@Param("userId") Long userId);
    int insert(UserPermission up);
    int deleteByUserIdAndPermission(@Param("userId") Long userId, @Param("permission") String permission);
} 