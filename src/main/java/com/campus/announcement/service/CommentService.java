package com.campus.announcement.service;

import com.campus.announcement.model.Comment;
import java.util.List;

public interface CommentService {
    Comment findById(Long id);
    List<Comment> findByTarget(String targetType, Long targetId);
    List<Comment> findByTargetWithLikeInfo(String targetType, Long targetId, Long userId);
    boolean addComment(Comment comment);
    boolean deleteComment(Long id);
} 