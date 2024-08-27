package com.kosta.dto;

import com.kosta.entity.AttendCourse;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AttendCourseDTO {
    private User student;
    private Course course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AttendCourseDTO(User student, Course course) {
        this.student = student;
        this.course = course;
    }

//    public AttendCourse setAttendCourse() {
//        AttendCourse attendCourse = new AttendCourse();
//        User user = new User();
//        attendCourse.setStudent(user.getUsername());
//        attendCourse.setCourse();
//        return user;
//    }
    public AttendCourse toEntity() {
        return new AttendCourse(student, course);
    }


}
