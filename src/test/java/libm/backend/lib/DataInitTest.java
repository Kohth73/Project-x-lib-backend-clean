package libm.backend.lib;

import libm.backend.lib.entity.User;
import libm.backend.lib.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DataInitTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testInitialUserData() {
        // Check if initial monitor@example.com exists
        assertThat(userRepository.findByEmail("monitor@example.com")).isPresent();
    }
}





