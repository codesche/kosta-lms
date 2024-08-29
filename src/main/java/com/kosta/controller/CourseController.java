package com.kosta.controller;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.dto.CourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import com.kosta.repository.CourseRepository;
import com.kosta.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course/*")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/add")
    public String addPage() {
        return "/course/form";
    }

    @PostMapping("/add")
    public String addCourse(CourseDTO courseDTO, @AuthenticationPrincipal User user) {
        try {
            courseService.save(courseDTO, user);
            return "redirect:/course/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/list")
    public String CourseListPage(Model model, @AuthenticationPrincipal User user) {
        List<CourseDTO> courseList = courseService.findAll(user);
        model.addAttribute("list", courseList);
        return "course/list";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        try {
            CourseDTO courseList = courseService.findById(id, user);
            model.addAttribute("course", courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "/course/detail";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        try {
            courseService.deleteById(id, user);
            return "redirect:/course/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/modify/{id}")
    public String modifyPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        try {
            CourseDTO courseList = courseService.findById(id, user);
            model.addAttribute("course", courseList);
            return "/course/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PatchMapping("/modify")
    public String modifyPage(CourseDTO courseDTO, @AuthenticationPrincipal User user) {
        try {
            courseService.update(courseDTO, user);
            return "redirect:/course/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


}
