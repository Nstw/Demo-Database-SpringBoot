package com.naome.demo.database.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    public Optional<Loan> findByLoanId(String loanId);
}
