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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProfileControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        String uniqueEmail = "profile_mock_" + System.currentTimeMillis() + "@example.com";

        testUser = new User();
        testUser.setEmail(uniqueEmail);
        testUser.setName("Profile Mock User");
        testUser.setPassword("$2a$10$Dow1o7kqzljzfo3v4ZxDVOIhXrD8");
        userRepository.save(testUser);
    }

    @Test
    void testGetProfile() throws Exception {
        mockMvc.perform(get("/api/profile")
                        .header("Authorization", "Bearer DUMMY_TOKEN"))
                .andExpect(status().isOk());
    }
}








