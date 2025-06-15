package com.campus.announcement.service.impl;
import com.campus.announcement.mapper.CommentLikeMapper;
import com.campus.announcement.model.CommentLike;
import com.campus.announcement.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentLikeServiceImpl implements CommentLikeService {
    @Autowired
    private CommentLikeMapper commentLikeMapper;
    @Override
    public boolean like(Long commentId, Long userId) {
        if (commentLikeMapper.findByCommentIdAndUserId(commentId, userId) == null) {
            CommentLike like = new CommentLike();
            like.setCommentId(commentId);
            like.setUserId(userId);
            return commentLikeMapper.insertLike(like) > 0;
        }
        return false;
    }
    @Override
    public boolean unlike(Long commentId, Long userId) {
        return commentLikeMapper.deleteLike(commentId, userId) > 0;
    }
    @Override
    public int countLikes(Long commentId) {
        return commentLikeMapper.countByCommentId(commentId);
    }
    @Override
    public boolean hasLiked(Long commentId, Long userId) {
        return commentLikeMapper.findByCommentIdAndUserId(commentId, userId) != null;
    }
} 