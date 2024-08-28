package com.kosta.service;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.User;

import java.util.List;

public interface AttendCourseService {
    boolean save(AttendCourseDTO attendCourseDTO, User user);

    List<AttendCourseDTO> findAll(User user);


}
