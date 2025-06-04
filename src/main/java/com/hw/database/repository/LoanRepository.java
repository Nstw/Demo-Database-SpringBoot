package com.hw.database.repository;

import com.hw.database.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByLoanId(String loanId);

    List<Loan> findByPerson_PersonId(String personId);

    List<Loan> findByStatus(String status);
}