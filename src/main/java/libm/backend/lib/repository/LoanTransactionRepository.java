package libm.backend.lib.repository;

import libm.backend.lib.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
    List<LoanTransaction> findByMemberId(Long memberId);
}
