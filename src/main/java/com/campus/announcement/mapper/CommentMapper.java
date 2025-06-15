package com.campus.announcement.mapper;

import com.campus.announcement.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper {
    Comment findById(@Param("id") Long id);
    List<Comment> findByTarget(@Param("targetType") String targetType, @Param("targetId") Long targetId);
    int insertComment(Comment comment);
    int deleteComment(@Param("id") Long id);
} 