package com.kosta.dto;

import com.kosta.entity.Course;
import com.kosta.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private int hourPeriod;
    private String teacher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Course toEntity(CourseDTO courseDTO, User user) {
        return Course.builder()
                .name(courseDTO.getName())
                .description(courseDTO.getDescription())
                .hourPeriod(courseDTO.getHourPeriod())
                .teacher(user)
                .build();
    }
}
