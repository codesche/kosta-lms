package com.kosta.controller;

import com.kosta.dto.UserDTO;
import com.kosta.entity.User;
import com.kosta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping("/add")
//    public String addPage() {
//        return "/user/form";
//    }
//
//    @PostMapping("/add")
//    public String addUser(UserDTO userDTO) {
//        try {
//            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//            userService.save(userDTO);
//            return "redirect:/user/list";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }

    @GetMapping("/list")
    public String userListPage(Model model, @AuthenticationPrincipal User user) {
        List<UserDTO> userList = userService.findAll(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping("/detail/{id}")
    public String userDetailPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        try {
            UserDTO userList = userService.findById(id, user);
            model.addAttribute("userList", userList);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "/user/detail";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, @AuthenticationPrincipal User user) {
        try {
            userService.deleteById(id, user);
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/modify/{id}")
    public String userModifyPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        try {
            UserDTO userList = userService.findById(id, user);
            System.out.println(userList);
            model.addAttribute("userList", userList);
            return "/user/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PatchMapping("/modify")
    public String userModifyPage(UserDTO userDTO) {
        try {
            userService.update(userDTO);
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
