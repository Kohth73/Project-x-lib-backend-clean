package libm.backend.lib;

import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.User;
import libm.backend.lib.repository.RoleRepository;
import libm.backend.lib.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(RoleRepository roleRepository,
                    UserRepository userRepository,
                    PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // --- Create ROLE_ADMIN ---
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName("ROLE_ADMIN");
                    r.setDescription("Administrator role");
                    return roleRepository.save(r);
                });

        // --- Create ROLE_USER ---
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName("ROLE_USER");
                    r.setDescription("Regular user role");
                    return roleRepository.save(r);
                });

        // --- Create Admin User ---
        if (!userRepository.existsByEmail("admin@example.com")) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));

            // initialize roles set if null
            if (admin.getRoles() == null) {
                admin.setRoles(new HashSet<>());
            }
            admin.getRoles().add(adminRole);

            userRepository.save(admin);
        }

        // --- Create Regular User ---
        if (!userRepository.existsByEmail("user@example.com")) {
            User user = new User();
            user.setName("User");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));

            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }
            user.getRoles().add(userRole);

            userRepository.save(user);
        }

        System.out.println("Data initialization completed.");
    }
}







