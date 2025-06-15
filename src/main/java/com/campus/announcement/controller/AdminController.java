package com.campus.announcement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private com.campus.announcement.service.EventRegistrationService eventRegistrationService;

    @Autowired
    private com.campus.announcement.service.UserService userService;

    private boolean isAdminOrTeacher(javax.servlet.http.HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj instanceof com.campus.announcement.model.User) {
            String role = ((com.campus.announcement.model.User) userObj).getRole();
            return "admin".equals(role) || "teacher".equals(role);
        }
        return false;
    }

    @GetMapping("/backup")
    public String backup(javax.servlet.http.HttpSession session) {
        if (!isAdminOrTeacher(session)) return "redirect:/error";
        return "admin/backup";
    }

    @PostMapping("/backup/export")
    public ResponseEntity<InputStreamResource> exportBackup(javax.servlet.http.HttpSession session) throws Exception {
        if (!isAdminOrTeacher(session)) return ResponseEntity.status(403).build();
        String fileName = "backup_" + System.currentTimeMillis() + ".sql";
        // Windows下用绝对路径和cmd /c
        String command = "cmd /c \"D:\\phpstudy_pro\\Extensions\\MySQL5.7.26\\bin\\mysqldump.exe\" -u root -pliuwei123 campus_announcement_system";
        Process process = Runtime.getRuntime().exec(command);
        java.io.InputStream is = process.getInputStream();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(is));
    }

    @GetMapping("/logs")
    public String logs(javax.servlet.http.HttpSession session, org.springframework.ui.Model model) throws java.io.IOException {
        if (!isAdminOrTeacher(session)) return "redirect:/error";
        java.nio.file.Path logPath = java.nio.file.Paths.get("logs/application.log");
        String logContent = "";
        if (java.nio.file.Files.exists(logPath)) {
            logContent = new String(java.nio.file.Files.readAllBytes(logPath), java.nio.charset.StandardCharsets.UTF_8);
        } else {
            logContent = "暂无日志内容";
        }
        model.addAttribute("logs", logContent);
        return "admin/logs";
    }

    @PostMapping("/logs/clear")
    public String clearLogs(javax.servlet.http.HttpSession session) throws java.io.IOException {
        if (!isAdminOrTeacher(session)) return "redirect:/error";
        java.nio.file.Path logPath = java.nio.file.Paths.get("logs/application.log");
        java.nio.file.Files.write(logPath, "".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        return "redirect:/admin/logs";
    }

    @GetMapping("/logs/download")
    public ResponseEntity<InputStreamResource> downloadLog(javax.servlet.http.HttpSession session) throws Exception {
        if (!isAdminOrTeacher(session)) return ResponseEntity.status(403).build();
        Path logPath = Paths.get("logs/application.log");
        java.io.InputStream is = Files.newInputStream(logPath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=application.log")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(is));
    }

    @GetMapping("/registrations")
    public String registrations(javax.servlet.http.HttpSession session, Model model) {
        if (!isAdminOrTeacher(session)) return "redirect:/error";
        java.util.List<com.campus.announcement.model.EventRegistration> registrations = eventRegistrationService.findAll();
        model.addAttribute("registrations", registrations);
        return "admin/registrations";
    }

    @GetMapping("/system")
    public String system(javax.servlet.http.HttpSession session) {
        if (!isAdminOrTeacher(session)) return "redirect:/error";
        return "admin/system";
    }
} 