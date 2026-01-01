package libm.backend.lib.controller;

import libm.backend.lib.entity.User;
import libm.backend.lib.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        String uniqueEmail = "auth_mock_" + System.currentTimeMillis() + "@example.com";

        testUser = new User();
        testUser.setEmail(uniqueEmail);
        testUser.setName("Auth Mock User");
        testUser.setPassword("$2a$10$Dow1o7kqzljzfo3v4ZxDVOIhXrD8");
        userRepository.save(testUser);
    }

    @Test
    void testLoginEndpoint() throws Exception {
        String loginJson = String.format("""
                {
                  "email": "%s",
                  "password": "monitor123"
                }
                """, testUser.getEmail());

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(loginJson))
                .andExpect(status().isOk());
    }
}







