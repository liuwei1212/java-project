package com.campus.announcement.controller;

import com.campus.announcement.model.Announcement;
import com.campus.announcement.service.AnnouncementService;
import com.campus.announcement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Announcement> announcements = announcementService.findAll();
        model.addAttribute("announcements", announcements);
        return "announcement/list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        if (user == null || (!"admin".equals(user.getRole()) && !"teacher".equals(user.getRole()))) {
            return "redirect:/error";
        }
        return "announcement/add";
    }

    @PostMapping("/add")
    public String add(Announcement announcement, HttpSession session) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        if (user == null || (!"admin".equals(user.getRole()) && !"teacher".equals(user.getRole()))) {
            return "redirect:/error";
        }
        announcement.setAuthorId(user.getId());
        announcementService.addAnnouncement(announcement);
        return "redirect:/announcement/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("announcement", announcementService.findById(id));
        return "announcement/edit";
    }

    @PostMapping("/edit")
    public String edit(Announcement announcement) {
        announcementService.updateAnnouncement(announcement);
        return "redirect:/announcement/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return "redirect:/announcement/list";
    }

    @GetMapping("/top/{id}")
    public String top(@PathVariable Long id) {
        Announcement announcement = announcementService.findById(id);
        if (announcement != null) {
            announcement.setIsTop(true);
            announcementService.updateAnnouncement(announcement);
        }
        return "redirect:/announcement/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        com.campus.announcement.model.Announcement announcement = announcementService.findById(id);
        model.addAttribute("announcement", announcement);
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        java.util.List<com.campus.announcement.model.Comment> comments = commentService.findByTargetWithLikeInfo("announcement", id, user != null ? user.getId() : null);
        model.addAttribute("comments", comments);
        return "announcement/detail";
    }
} 