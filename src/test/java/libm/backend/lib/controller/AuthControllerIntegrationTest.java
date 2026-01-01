package libm.backend.lib.controller;

import libm.backend.lib.entity.User;
import libm.backend.lib.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AuthControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Use a unique email to avoid duplicate entry errors
        String uniqueEmail = "auth_test_" + System.currentTimeMillis() + "@example.com";

        testUser = new User();
        testUser.setEmail(uniqueEmail);
        testUser.setName("Auth Test User");
        testUser.setPassword("$2a$10$Dow1o7kqzljzfo3v4ZxDVOIhXrD8"); // existing hash for consistency
        userRepository.save(testUser);
    }

    @Test
    void testUserCreation() {
        assertThat(userRepository.findByEmail(testUser.getEmail())).isPresent();
    }
}








