package libm.backend.lib.config;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.repository.PermissionRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final PermissionRepository permissionRepository;

    public DataInitializer(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    public void init() {

        createPermissionIfNotExists("BOOK_READ", "BOOK_READ", "Read books");
        createPermissionIfNotExists("BOOK_CREATE", "BOOK_CREATE", "Create books");
        createPermissionIfNotExists("BOOK_UPDATE", "BOOK_UPDATE", "Update books");
        createPermissionIfNotExists("BOOK_DELETE", "BOOK_DELETE", "Delete books");
    }

    private void createPermissionIfNotExists(String name, String code, String description) {

        permissionRepository.findByName(name)
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setCode(code);          // <-- important
                    permission.setDescription(description);
                    return permissionRepository.save(permission);
                });
    }
}

