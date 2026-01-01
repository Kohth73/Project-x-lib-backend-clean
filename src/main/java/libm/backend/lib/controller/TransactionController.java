package libm.backend.lib.controller;

import libm.backend.lib.entity.LoanTransaction;
import libm.backend.lib.service.LoanTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final LoanTransactionService loanTransactionService;

    @Autowired
    public TransactionController(LoanTransactionService loanTransactionService) {
        this.loanTransactionService = loanTransactionService;
    }

    @PostMapping("/borrow")
    public LoanTransaction borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return loanTransactionService.borrowBook(memberId, bookId);
    }

    @PostMapping("/return/{transactionId}")
    public LoanTransaction returnBook(@PathVariable Long transactionId) {
        return loanTransactionService.returnBook(transactionId);
    }

    @GetMapping("/member/{memberId}")
    public List<LoanTransaction> getLoansByMember(@PathVariable Long memberId) {
        return loanTransactionService.getLoansByMember(memberId);
    }

    @GetMapping("/all")
    public List<LoanTransaction> getAllLoans() {
        return loanTransactionService.getAllLoans();
    }
}


