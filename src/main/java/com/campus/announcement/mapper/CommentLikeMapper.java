package com.campus.announcement.mapper;
import com.campus.announcement.model.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface CommentLikeMapper {
    int insertLike(CommentLike like);
    int deleteLike(@Param("commentId") Long commentId, @Param("userId") Long userId);
    int countByCommentId(@Param("commentId") Long commentId);
    CommentLike findByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
} 