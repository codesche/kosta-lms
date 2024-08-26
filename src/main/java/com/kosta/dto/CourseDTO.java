package com.kosta.dto;

import com.kosta.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
public class CourseDTO {

    private String name;
    private String description;
    private int hourPeriod;
    private User teacher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
