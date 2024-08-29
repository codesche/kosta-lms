package com.kosta.service.Impl;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.dto.CourseDTO;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import com.kosta.repository.CourseRepository;
import com.kosta.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                        .teacher(course.getTeacher().getUsername())
                        .createdAt(course.getCreatedAt())
                        .updatedAt(course.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long id, User user) {
        Course course = courseRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .teacher(course.getTeacher().getUsername())
                .description(course.getDescription())
                .hourPeriod(course.getHourPeriod())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteById(Long id, User user) {
        CourseDTO courseDTO = findById(id, user);
        courseRepository.deleteById(courseDTO.getId());
    }

    @Override
    public boolean update(CourseDTO courseDTO, User user) {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(RuntimeException::new);
        course.modifyCourse(courseDTO.getName(),
                            courseDTO.getDescription(),
                            courseDTO.getHourPeriod());
        try {
            courseRepository.save(course);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
