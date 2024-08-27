package com.kosta.controller;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.User;
import com.kosta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/add")
    public String addPage() {
        return "/admin/form";
    }

    @PostMapping("/add")
    public String addAttendCourse(AttendCourseDTO attendCourseDTO, Model model, @AuthenticationPrincipal User user) {
        return "redirect:/admin/list";
    }

    @GetMapping("/list")
    public String AdminListPage() {
        return "admin/list";
    }

}
