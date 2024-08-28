package com.kosta.service.Impl;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import com.kosta.repository.AttendCourseRepository;
import com.kosta.repository.CourseRepository;
import com.kosta.service.AttendCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendCourseServiceImpl implements AttendCourseService {

    private final AttendCourseRepository attendCourseRepository;
    private final CourseRepository courseRepository;

    @Override
    public boolean save(AttendCourseDTO attendCourseDTO, User user) {
        Course course = courseRepository.findById(attendCourseDTO.getCourseId()).get();
        attendCourseDTO.setCourse(course);
        attendCourseRepository.save(attendCourseDTO.toEntity(user));
        return false;
    }

    @Override
    public List<AttendCourseDTO> findAll(User user) {
        List<AttendCourse> list = attendCourseRepository.findAll();
        return list.stream().map(attendCourse ->
                AttendCourseDTO.builder()
                        .studentId(attendCourse.getId())
                        .studentName(attendCourse.getStudent().getUsername())
                        .courseName(attendCourse.getCourse().getName())
                        .createdAt(attendCourse.getCreatedAt())
                        .updatedAt(attendCourse.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }
}
