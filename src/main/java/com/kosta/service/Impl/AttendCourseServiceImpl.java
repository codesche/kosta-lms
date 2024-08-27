package com.kosta.service.Impl;

import com.kosta.dto.AttendCourseDTO;
import com.kosta.entity.AttendCourse;
import com.kosta.entity.User;
import com.kosta.repository.AttendCourseRepository;
import com.kosta.service.AttendCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendCourseServiceImpl implements AttendCourseService {

    private final AttendCourseRepository attendCourseRepository;
//    @Override
//    public List<AttendCourseDTO> findAll() {
//        AttendCourseDTO attendCourseDTO = AttendCourseDTO.builder().build();
//        return attendCourseRepository.findAll();
//    }
//
    @Override
    public AttendCourse save(AttendCourseDTO attendCourseDTO, User user) {
        return attendCourseDTO.toEntity();
    }
}
