package com.campus.announcement.controller;

import com.campus.announcement.model.User;
import com.campus.announcement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model, HttpSession session) {
        if (userService.register(user)) {
            // 注册成功后自动登录
            User loginUser = userService.login(user.getUsername(), user.getPassword());
            session.setAttribute("user", loginUser);
            return "redirect:/";
        } else {
            model.addAttribute("error", "用户名已存在");
            return "user/register";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("user", userService.findById(user.getId()));
        return "user/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(User user, HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) return "redirect:/user/login";
        user.setId(sessionUser.getId());
        if (userService.updateUser(user)) {
            session.setAttribute("user", userService.findById(user.getId()));
            model.addAttribute("msg", "信息更新成功");
        } else {
            model.addAttribute("error", "信息更新失败");
        }
        model.addAttribute("user", userService.findById(user.getId()));
        return "user/profile";
    }

    @GetMapping("/list")
    public String userList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            return "redirect:/error";
        }
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
} 