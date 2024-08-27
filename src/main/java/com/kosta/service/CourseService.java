package com.kosta.service;

import com.kosta.dto.CourseDTO;
import com.kosta.entity.User;

import java.util.List;

public interface CourseService {

    boolean save(CourseDTO courseDTO, User user);

    List<CourseDTO> findAll(User user);

//    List<CourseDTO> findAll();
}
