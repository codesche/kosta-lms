package com.kosta.service.Impl;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.dto.CourseDTO;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import com.kosta.repository.CourseRepository;
import com.kosta.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public boolean save(CourseDTO courseDTO, User user) {
        courseRepository.save(CourseDTO.toEntity(courseDTO, user));
        return false;
    }

    @Override
    public List<CourseDTO> findAll(User user) {
        List<Course> list = courseRepository.findAll();
        return list.stream().map(course ->
                CourseDTO.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .description(course.getDescription())
                        .hourPeriod(course.getHourPeriod())
                        .teacher(user.getUsername())
                        .createdAt(course.getCreatedAt())
                        .updatedAt(course.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());
    }
}
