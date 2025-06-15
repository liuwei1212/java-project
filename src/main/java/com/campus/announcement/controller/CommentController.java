package com.campus.announcement.controller;

import com.campus.announcement.model.Comment;
import com.campus.announcement.service.CommentService;
import com.campus.announcement.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/add")
    public String add(Comment comment) {
        commentService.addComment(comment);
        if ("announcement".equals(comment.getTargetType())) {
            return "redirect:/announcement/detail/" + comment.getTargetId();
        } else if ("event".equals(comment.getTargetType())) {
            return "redirect:/event/detail/" + comment.getTargetId();
        } else {
            // 其他类型可按需处理
            return "redirect:/";
        }
    }

    @GetMapping("/list/{targetType}/{targetId}")
    public String list(@PathVariable String targetType, @PathVariable Long targetId, Model model) {
        List<Comment> comments = commentService.findByTarget(targetType, targetId);
        model.addAttribute("comments", comments);
        return "comment/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/";
    }

    @PostMapping("/like")
    @ResponseBody
    public java.util.Map<String, Object> like(@RequestParam Long commentId, HttpSession session) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        if (user == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        boolean liked = commentLikeService.like(commentId, user.getId());
        int count = commentLikeService.countLikes(commentId);
        result.put("success", liked);
        result.put("count", count);
        return result;
    }

    @PostMapping("/unlike")
    @ResponseBody
    public java.util.Map<String, Object> unlike(@RequestParam Long commentId, HttpSession session) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        if (user == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        boolean unliked = commentLikeService.unlike(commentId, user.getId());
        int count = commentLikeService.countLikes(commentId);
        result.put("success", unliked);
        result.put("count", count);
        return result;
    }
} 