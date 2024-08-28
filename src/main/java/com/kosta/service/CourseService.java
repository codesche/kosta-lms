package com.kosta.service;

import com.kosta.dto.CourseDTO;
import com.kosta.entity.User;

import java.util.List;

public interface CourseService {

    boolean save(CourseDTO courseDTO, User user);

    List<CourseDTO> findAll(User user);

    CourseDTO findById(Long id, User user);

    void deleteById(Long id, User user) throws Exception;

    boolean update(CourseDTO courseDTO, User user);
}
