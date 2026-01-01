package libm.backend.lib.service;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.repository.PermissionRepository;
import libm.backend.lib.service.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Test
    void testCreatePermission() {
        // Fixed: using constructor with name, code, description
        Permission permission = new Permission("CREATE_BOOK", "CREATE_BOOK", "Allows creating books");

        // Mock repository save
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        // Call service method with correct arguments
        Permission saved = permissionService.createPermission(
                permission.getName(),
                permission.getCode(),
                permission.getDescription()
        );

        assertNotNull(saved);
        assertEquals("CREATE_BOOK", saved.getName());
        assertEquals("CREATE_BOOK", saved.getCode());
        assertEquals("Allows creating books", saved.getDescription());
        verify(permissionRepository, times(1)).save(any(Permission.class));
    }
}






