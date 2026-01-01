package libm.backend.lib.config;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.entity.Role;
import libm.backend.lib.repository.PermissionRepository;
import libm.backend.lib.repository.RoleRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class RoleDataInitializer {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleDataInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    public void init() {
        // Create roles if not exist
        Role adminRole = createRoleIfNotExists("ADMIN", "Administrator with full permissions");
        Role librarianRole = createRoleIfNotExists("LIBRARIAN", "Librarian role");
        Role memberRole = createRoleIfNotExists("MEMBER", "Regular member");
        Role roleAdmin = createRoleIfNotExists("ROLE_ADMIN", "Administrator role");
        Role roleUser = createRoleIfNotExists("ROLE_USER", "Regular user role");

        // Assign permissions using 'code'
        assignPermissionsToRole(adminRole, new String[]{"BOOK_READ", "BOOK_CREATE", "BOOK_UPDATE", "BOOK_DELETE"});
        assignPermissionsToRole(librarianRole, new String[]{"BOOK_READ", "BOOK_CREATE", "BOOK_UPDATE"});
        assignPermissionsToRole(memberRole, new String[]{"BOOK_READ"});
    }

    private Role createRoleIfNotExists(String name, String description) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            return roleRepository.save(role);
        });
    }

    private void assignPermissionsToRole(Role role, String[] permissionCodes) {
        Set<Permission> permissions = new HashSet<>();
        for (String code : permissionCodes) {
            permissionRepository.findByCode(code).ifPresent(permissions::add);
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
    }
}

