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
@RequestMapping("/admin/user")
public class UserManageController {
    @Autowired
    private UserService userService;

    private boolean isAdmin(HttpSession session) {
        Object userObj = session.getAttribute("user");
        return userObj instanceof User && "admin".equals(((User) userObj).getRole());
    }

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/error";
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user_manage";
    }

    @GetMapping("/add")
    public String addPage(HttpSession session) {
        if (!isAdmin(session)) return "redirect:/error";
        return "admin/user_add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute User user, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/error";
        userService.addUser(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/error";
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user_edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute User user, @RequestParam(required = false) String[] permissions, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/error";
        userService.updateUser(user);
        // 更新权限
        return "redirect:/admin/user/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/error";
        userService.deleteUser(id);
        return "redirect:/admin/user/list";
    }

    @PostMapping("/resetpwd")
    public String resetPwd(@RequestParam Long id, @RequestParam String newPwd, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/error";
        User user = userService.findById(id);
        if (user != null) {
            user.setPassword(newPwd);
            userService.updateUser(user);
        }
        return "redirect:/admin/user/list";
    }
} 