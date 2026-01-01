package libm.backend.lib.repository;

import libm.backend.lib.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {}
