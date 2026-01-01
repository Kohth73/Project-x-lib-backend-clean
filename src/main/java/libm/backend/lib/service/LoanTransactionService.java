package libm.backend.lib.service;

import libm.backend.lib.entity.LoanTransaction;
import java.util.List;

public interface LoanTransactionService {

    LoanTransaction borrowBook(Long memberId, Long bookId);

    LoanTransaction returnBook(Long transactionId);

    List<LoanTransaction> getLoansByMember(Long memberId);

    List<LoanTransaction> getAllLoans();
}
