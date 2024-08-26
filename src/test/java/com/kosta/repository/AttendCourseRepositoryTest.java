package com.kosta.repository;

import com.kosta.entity.AttendCourse;
import com.kosta.entity.Course;
import com.kosta.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttendCourseRepositoryTest {

    @Autowired
    AttendCourseRepository attendCourseRepository;

    @DisplayName("수강 등록 테스트")
    @Test
    public void registerCourseTest() {
        // given (사전 준비)
        AttendCourse attendCourse = AttendCourse.builder()
                .student(User.builder()
                        .name("진국이")
                        .build())
                .course(Course.builder()
                        .name("Java의 정석")
                        .build())
                .price(10000)
                .build();

        // when (테스트 진행할 행위)
        AttendCourse savedAttendCourse = attendCourseRepository.save(attendCourse);

        // then (행위에 대한 결과 검증)
        assertThat(savedAttendCourse).isEqualTo(attendCourse);
    }


    @DisplayName("특정한 수강중인 강의 조회")
    @Test
    public void findByIdTest() {
        // given (사전 준비)
        AttendCourse attendCourse = AttendCourse.builder()
                .student(User.builder()
                        .name("진국이")
                        .build())
                .course(Course.builder()
                        .name("Java의 정석")
                        .build())
                .price(10000)
                .build();
        AttendCourse savedAttendCourse = attendCourseRepository.save(attendCourse);

        // when (테스트 진행할 행위)
        AttendCourse foundAttendCourse = attendCourseRepository.findById(savedAttendCourse.getId()).get();

        // then (행위에 대한 결과 검증)
        assertThat(foundAttendCourse).isNotNull();
        assertThat(foundAttendCourse.getId()).isEqualTo(savedAttendCourse.getId());
        assertThat(foundAttendCourse.getStudent()).isEqualTo(savedAttendCourse.getStudent());
        assertThat(foundAttendCourse.getCourse()).isEqualTo(savedAttendCourse.getCourse());
        assertThat(foundAttendCourse.getPrice()).isEqualTo(savedAttendCourse.getPrice());
    }


    @DisplayName("특정 수강중인 강의 삭제")
    @Test
    public void deleteAttendCourse() {
        // given (사전 준비)
        int originalSize = attendCourseRepository.findAll().size();
        AttendCourse attendCourse = AttendCourse.builder()
                .student(User.builder()
                        .name("진국이")
                        .build())
                .course(Course.builder()
                        .name("Java의 정석")
                        .build())
                .price(10000)
                .build();
        AttendCourse savedAttendCourse = attendCourseRepository.save(attendCourse);

        // when (테스트 진행할 행위)
        attendCourseRepository.deleteById(savedAttendCourse.getId());
        int newSize = attendCourseRepository.findAll().size();

        // then (행위에 대한 결과 검증)
        assertThat(originalSize).isEqualTo(newSize);
    }
}