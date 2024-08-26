package com.kosta.dto;

import com.kosta.entity.Course;
import com.kosta.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendCourseDTO {
    private User student;
    private Course course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
