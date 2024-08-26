package com.kosta.repository;

import com.kosta.entity.AttendCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendCourseRepository extends JpaRepository<AttendCourse, Long> {

}
