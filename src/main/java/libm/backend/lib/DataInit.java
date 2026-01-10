package libm.backend.lib;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.User;
import libm.backend.lib.repository.PermissionRepository;
import libm.backend.lib.repository.RoleRepository;
import libm.backend.lib.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("prod") // Only run in production profile
public class DataInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(RoleRepository roleRepository,
                    UserRepository userRepository,
                    PermissionRepository permissionRepository,
                    PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // --- Permissions ---
        Permission pView = createPermissionIfNotExists(
                "Profile View", "PROFILE_VIEW", "Can view profiles");

        Permission pUpdateSelf = createPermissionIfNotExists(
                "Profile Update Self", "PROFILE_UPDATE_SELF", "Can update own profile");

        Permission pUpdateAny = createPermissionIfNotExists(
                "Profile Update Any", "PROFILE_UPDATE_ANY", "Can update any profile");

        // --- Roles ---
        Role adminRole = createOrUpdateRole(
                "ROLE_ADMIN",
                "Administrator role",
                List.of(pView, pUpdateSelf, pUpdateAny)
        );

        Role userRole = createOrUpdateRole(
                "ROLE_USER",
                "Regular user role",
                List.of()
        );

        Role monitorRole = createOrUpdateRole(
                "ROLE_MONITOR",
                "Monitor role",
                List.of()
        );

        // --- Users (create ONLY if missing) ---
        createUserIfNotExists("Admin", "admin@prod.com", "admin123", adminRole);
        createUserIfNotExists("User", "user@example.com", "user123", userRole);
        createUserIfNotExists("Prometheus Monitor", "monitor@example.com", "monitor123", monitorRole);

        System.out.println("Production DataInit completed successfully.");
    }

    // ------------------------------------------------------
    // Helpers
    // ------------------------------------------------------

    private Permission createPermissionIfNotExists(String name, String code, String description) {
        return permissionRepository.findByCode(code)
                .orElseGet(() -> {
                    Permission p = new Permission();
                    p.setName(name);
                    p.setCode(code);
                    p.setDescription(description);
                    return permissionRepository.save(p);
                });
    }

    private Role createOrUpdateRole(String name, String description, List<Permission> permissions) {
        return roleRepository.findByName(name)
                .map(role -> {

                    // Update description if missing
                    if (role.getDescription() == null || role.getDescription().isBlank()) {
                        role.setDescription(description);
                    }

                    // Attach missing permissions only
                    if (permissions != null && !permissions.isEmpty()) {
                        Set<Permission> currentPerms = role.getPermissions();
                        boolean updated = currentPerms.addAll(permissions);
                        if (updated) {
                            roleRepository.save(role);
                        }
                    }

                    return role;
                })
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(name);
                    role.setDescription(description);
                    role.setPermissions(new HashSet<>(permissions));
                    return roleRepository.save(role);
                });
    }

    private void createUserIfNotExists(String name,
                                       String email,
                                       String rawPassword,
                                       Role role) {

        userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRoles(Set.of(role));
            return userRepository.save(user);
        });
    }
}

