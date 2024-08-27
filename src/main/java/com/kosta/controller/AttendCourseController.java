package com.kosta.controller;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.User;
import com.kosta.service.AttendCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attend/*")
public class AttendCourseController {

    private final AttendCourseService attendCourseService;

    @GetMapping("/add")
    public String addPage() {
        return "/attend/form";
    }

    @PostMapping("/add")
    public String addAttendCourse(AttendCourseDTO attendCourseDTO, Model model, @AuthenticationPrincipal User user) {
        AttendCourse attendCourse = attendCourseDTO.toEntity();
        attendCourseService.save(attendCourseDTO, user);
        model.addAttribute("attend", attendCourseDTO);
        return "redirect:/attend/list";
    }

    @GetMapping("/list")
    public String AttendCourseListPage(Model model) {
//        List<AttendCourse> list = attendCourseService.findAll();
//        model.addAttribute("list", list);
        return "/attend/list";
    }



}
