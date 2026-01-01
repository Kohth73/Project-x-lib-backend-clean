package libm.backend.lib.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fine")
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanTransaction loan;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Boolean paid = false;

    public Fine() {}

    public Fine(LoanTransaction loan, Double amount, Boolean paid) {
        this.loan = loan;
        this.amount = amount;
        this.paid = paid;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LoanTransaction getLoan() { return loan; }
    public void setLoan(LoanTransaction loan) { this.loan = loan; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }
}

