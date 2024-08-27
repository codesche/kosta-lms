package com.kosta.controller;

import com.kosta.dto.UserDTO;
import com.kosta.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    @GetMapping("/sign-up")
    public String signUp() {
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(UserDTO userDTO) {
        userService.signUp(userDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return userService.isLogin() ? "redirect:/" : "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res) {
        new SecurityContextLogoutHandler()
                .logout(req, res, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
