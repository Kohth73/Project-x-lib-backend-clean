package libm.backend.lib.service;

import libm.backend.lib.repository.RoleRepository;
import libm.backend.lib.service.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    // Add any other dependencies here if your service requires
    // @Mock private PermissionService permissionService;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole() {
        // your test code
    }
}




