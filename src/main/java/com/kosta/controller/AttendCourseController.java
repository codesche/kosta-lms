package com.kosta.controller;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.dto.CourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.User;
import com.kosta.service.AttendCourseService;
import com.kosta.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attend/*")
public class AttendCourseController {

    private final AttendCourseService attendCourseService;
    private final CourseService courseService;

    @GetMapping("/add")
    public String addPage(Model model, @AuthenticationPrincipal User user) {
        List<CourseDTO> courseList = courseService.findAll(user);
        model.addAttribute("course", courseList);
        try {
            return "/attend/form";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/add")
    public String addAttendCourse(AttendCourseDTO attendCourseDTO, @AuthenticationPrincipal User user) {
        try {
            attendCourseService.save(attendCourseDTO, user);
            return "redirect:/attend/list";
        } catch (Exception e) {
             return "error";
        }
    }

    @GetMapping("/list")
    public String AttendCourseListPage(Model model, User user) {
        List<AttendCourseDTO> list = attendCourseService.findAll(user);
        model.addAttribute("list", list);
        return "/attend/list";
    }

}
