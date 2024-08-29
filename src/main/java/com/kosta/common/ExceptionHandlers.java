package com.kosta.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(Exception.class)
    public static String defaultExceptionHandler(Exception e, Model model) {
        log.error(e.getMessage());
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        model.addAttribute("errMsg", e.getMessage());
        model.addAttribute("stackTrace", sw);
        return "error";
    }
}