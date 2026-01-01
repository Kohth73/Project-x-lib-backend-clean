package libm.backend.lib.service;

import libm.backend.lib.entity.User;
import libm.backend.lib.repository.RoleRepository;
import libm.backend.lib.repository.UserRepository;
import libm.backend.lib.service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() {
        User u = new User();
        u.setEmail("abc@test.com");

        when(userRepository.findByEmail("abc@test.com")).thenReturn(Optional.of(u));

        // using new getUserByEmail defined on interface/impl
        Optional<User> result = userService.getUserByEmail("abc@test.com");
        assertNotNull(result);
    }
}








