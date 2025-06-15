package com.campus.announcement.service;
import com.campus.announcement.model.CommentLike;
public interface CommentLikeService {
    boolean like(Long commentId, Long userId);
    boolean unlike(Long commentId, Long userId);
    int countLikes(Long commentId);
    boolean hasLiked(Long commentId, Long userId);
} 