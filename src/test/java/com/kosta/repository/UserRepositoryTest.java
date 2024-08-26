package com.kosta.repository;

import com.kosta.domain.UserRole;
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
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("회원가입 테스트")
    @Test
    public void signUpTest() {
        // given (사전 준비)
        User user = User.builder()
                .name("k2")
                .username("jonathan")
                .email("test@gmail.com")
                .password("1234")
                .role(UserRole.USER)
                .build();

        // when (테스트 진행할 행위)
        User savedUser = userRepository.save(user);

        // then (행위에 대한 결과 검증)
        assertThat(savedUser).isEqualTo(user);
    }


    @DisplayName("회원 전체 조회 테스트")
    @Test
    public void findAllUserTest() {
        // given (사전 준비)
        User user1 = User.builder()
                .username("유저1")
                .name("닉네임1")
                .email("test2@gmail.com")
                .password("1234")
                .role(UserRole.USER)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .username("유저2")
                .name("닉네임2")
                .email("test3@gmail.com")
                .password("5678")
                .role(UserRole.TEACHER)
                .build();
        userRepository.save(user2);

        // when (테스트 진행할 행위)
        List<User> userList = userRepository.findAll();

        // then (행위에 대한 결과 검증)
        assertThat(userList).isNotNull();
        assertThat(userList).size().isGreaterThanOrEqualTo(2);
        assertThat(userList.stream().anyMatch(article -> article.getUsername().equals("유저1"))).isTrue();
        assertThat(userList.stream().anyMatch(article -> article.getName().equals("닉네임2"))).isTrue();
    }


    @DisplayName("특정 회원 조회")
    @Test
    public void findByIdTest() {
        // given (사전 준비)
        User user = User.builder()
                .name("k3")
                .username("john")
                .email("test4@gmail.com")
                .password("12345")
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);

        // when (테스트 진행할 행위)
        User foundUser = userRepository.findById(savedUser.getId()).get();

        // then (행위에 대한 결과 검증)
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(foundUser.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(foundUser.getRole()).isEqualTo(savedUser.getRole());
    }


    @DisplayName("특정 회원 삭제")
    @Test
    public void deleteUserTest() {
        // given (사전 준비)
        int originalSize = userRepository.findAll().size();
        User user = User.builder()
                .name("k4")
                .username("john2")
                .email("test45@gmail.com")
                .password("123456")
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);

        // when (테스트 진행할 행위)
        userRepository.deleteById(savedUser.getId());
        int newSize = userRepository.findAll().size();

        // then (행위에 대한 결과 검증)
        assertThat(originalSize).isEqualTo(newSize);
    }


    @DisplayName("특정 회원 수정")
    @Test
    public void updateUserTest() {
        // given (사전 준비)
        User user = User.builder()
                .name("k5")
                .username("john3")
                .email("test456@gmail.com")
                .password("1234568")
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);

        // when (테스트 진행할 행위)
        User foundUser = userRepository.findById(savedUser.getId()).get();
        foundUser.setName("k6");
        foundUser.setUsername("johnny");
        foundUser.setEmail("test12@gmail.com");

        // then (행위에 대한 결과 검증)
        User changedUser = userRepository.findById(savedUser.getId()).get();
        assertThat(foundUser.getName()).isEqualTo(changedUser.getName());
        assertThat(foundUser.getUsername()).isEqualTo(changedUser.getUsername());
        assertThat(foundUser.getEmail()).isEqualTo(changedUser.getEmail());
    }

}