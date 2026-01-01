package libm.backend.lib.repository;

import libm.backend.lib.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    // Added method for deleting by email (Spring Data JPA will implement this automatically)
    void deleteByEmail(String email);
}





