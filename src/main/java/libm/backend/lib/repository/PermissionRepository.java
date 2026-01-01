package libm.backend.lib.repository;

import libm.backend.lib.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
    Optional<Permission> findByCode(String code);  // <-- Added for best practice
}


