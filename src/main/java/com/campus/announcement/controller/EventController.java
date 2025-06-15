package com.campus.announcement.controller;

import com.campus.announcement.model.Event;
import com.campus.announcement.model.EventRegistration;
import com.campus.announcement.service.EventService;
import com.campus.announcement.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRegistrationService eventRegistrationService;
    @Autowired
    private com.campus.announcement.service.CommentService commentService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "event/list";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session, Model model) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        System.out.println("[addPage] 当前用户: " + user + ", 角色: " + (user != null ? user.getRole() : "null"));
        if (user == null || (!"admin".equals(user.getRole()) && !"teacher".equals(user.getRole()))) {
            return "redirect:/error";
        }
        model.addAttribute("event", new com.campus.announcement.model.Event());
        return "event/add";
    }

    @PostMapping("/add")
    public String add(com.campus.announcement.model.Event event, HttpSession session) {
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        System.out.println("[add] 当前用户: " + user + ", 角色: " + (user != null ? user.getRole() : "null"));
        System.out.println("[add] event: " + event);
        if (user == null || (!"admin".equals(user.getRole()) && !"teacher".equals(user.getRole()))) {
            return "redirect:/error";
        }
        event.setAuthorId(user.getId());
        eventService.addEvent(event);
        return "redirect:/event/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "event/edit";
    }

    @PostMapping("/edit")
    public String edit(Event event) {
        eventService.updateEvent(event);
        return "redirect:/event/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/event/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        // 查询评论
        com.campus.announcement.model.User user = (com.campus.announcement.model.User) session.getAttribute("user");
        boolean hasRegistered = false;
        if (user != null) {
            EventRegistration reg = eventRegistrationService.findByEventIdAndUserId(id, user.getId());
            hasRegistered = reg != null;
        }
        model.addAttribute("hasRegistered", hasRegistered);
        // 评论区带点赞信息
        java.util.List<com.campus.announcement.model.Comment> comments = commentService.findByTargetWithLikeInfo("event", id, user != null ? user.getId() : null);
        model.addAttribute("comments", comments);
        java.util.List<EventRegistration> registrations = eventRegistrationService.findByEventId(id);
        model.addAttribute("registrations", registrations);
        return "event/detail";
    }

    @PostMapping("/register")
    public String register(EventRegistration registration, HttpSession session, Model model) {
        // 限制同一用户只能报名一次
        EventRegistration exist = eventRegistrationService.findByEventIdAndUserId(registration.getEventId(), registration.getUserId());
        if (exist == null) {
            eventRegistrationService.addRegistration(registration);
        }
        return "redirect:/event/detail/" + registration.getEventId();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }
} 