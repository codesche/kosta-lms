package com.kosta.dto;

import com.kosta.entity.AttendCourse;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendCourseDTO {
    private Long studentId;
    private Long courseId;
    private Course course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String studentName;
    private String courseName;
    private User student;
    private Long id;

    public AttendCourse toEntity(User user) {
        return AttendCourse.builder()
                .student(user)
                .course(course)
                .build();
    }

}
