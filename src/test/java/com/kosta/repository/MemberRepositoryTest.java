package com.kosta.repository;

import com.kosta.domain.UserRole;
import com.kosta.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("회원가입 테스트")
    @Test
    public void signUpTest() {
        // given (사전 준비)
        final User user = User.builder()
                .username("jonathan")
                .name("hms")
                .email("test@gmail.com")
                .password("1234")
                .role(UserRole.USER)
                .build();

        // when (테스트 진행할 행위)
        final User result = userRepository.save(user);

        // then (행위에 대한 결과 검증)
        assertThat(result.getUsername()).isEqualTo("jonathan");
        assertThat(result.getName()).isEqualTo("hms");
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getPassword()).isEqualTo("1234");
        assertThat(result.getRole()).isEqualTo(UserRole.USER);
    }

}