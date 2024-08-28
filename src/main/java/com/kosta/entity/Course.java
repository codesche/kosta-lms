package com.kosta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "hour_period")
    private int hourPeriod;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private User teacher;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Course(String name, String description, int hourPeriod, User teacher) {
        this.name = name;
        this.description = description;
        this.hourPeriod = hourPeriod;
        this.teacher = teacher;
    }

    public void modifyCourse(String name, String description, int hourPeriod) {
        this.name = name;
        this.description = description;
        this.hourPeriod = hourPeriod;
    }

}
