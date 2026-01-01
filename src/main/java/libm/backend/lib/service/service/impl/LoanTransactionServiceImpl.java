package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.Book;
import libm.backend.lib.entity.LoanTransaction;
import libm.backend.lib.entity.Member;
import libm.backend.lib.repository.LoanTransactionRepository;
import libm.backend.lib.repository.MemberRepository;
import libm.backend.lib.repository.BookRepository;
import libm.backend.lib.service.LoanTransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanTransactionServiceImpl implements LoanTransactionService {

    private final LoanTransactionRepository loanTransactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public LoanTransactionServiceImpl(LoanTransactionRepository loanTransactionRepository,
                                      MemberRepository memberRepository,
                                      BookRepository bookRepository) {
        this.loanTransactionRepository = loanTransactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public LoanTransaction borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        LoanTransaction transaction = new LoanTransaction();
        transaction.setMember(member);
        transaction.setBook(book);
        transaction.setLoanDate(LocalDate.now());      // FIXED
        transaction.setDueDate(LocalDate.now().plusDays(14)); // FIXED

        return loanTransactionRepository.save(transaction);
    }

    @Override
    public LoanTransaction returnBook(Long transactionId) {
        LoanTransaction transaction = loanTransactionRepository.findById(transactionId).orElseThrow();
        transaction.setReturnDate(LocalDate.now());
        return loanTransactionRepository.save(transaction);
    }

    @Override
    public List<LoanTransaction> getLoansByMember(Long memberId) {
        return loanTransactionRepository.findByMemberId(memberId);
    }

    @Override
    public List<LoanTransaction> getAllLoans() {
        return loanTransactionRepository.findAll();
    }
}
