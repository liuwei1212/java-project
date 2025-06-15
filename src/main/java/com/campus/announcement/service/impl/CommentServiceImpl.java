package com.campus.announcement.service.impl;

import com.campus.announcement.mapper.CommentMapper;
import com.campus.announcement.model.Comment;
import com.campus.announcement.service.CommentService;
import com.campus.announcement.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentLikeService commentLikeService;

    @Override
    public Comment findById(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> findByTarget(String targetType, Long targetId) {
        return commentMapper.findByTarget(targetType, targetId);
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insertComment(comment) > 0;
    }

    @Override
    public boolean deleteComment(Long id) {
        return commentMapper.deleteComment(id) > 0;
    }

    @Override
    public List<Comment> findByTargetWithLikeInfo(String targetType, Long targetId, Long userId) {
        List<Comment> comments = commentMapper.findByTarget(targetType, targetId);
        for (Comment c : comments) {
            c.setLikeCount(commentLikeService.countLikes(c.getId()));
            c.setLiked(userId != null && commentLikeService.hasLiked(c.getId(), userId));
        }
        return comments;
    }
} 