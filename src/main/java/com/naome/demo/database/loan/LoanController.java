package com.naome.demo.database.loan;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    // @GetMapping("/api/loans")
    // public List<Loan> getAllLoans() {
    // return List.of(
    // new Loan(1L, "L001", "Alice Smith", 15000.00, 36, "Approved", 3.5),
    // new Loan(2L, "L002", "Bob Johnson", 25000.00, 60, "Pending", 4.0),
    // new Loan(3L, "L003", "Charlie Brown", 10000.00, 24, "Rejected", 5.0));
    // }

    private final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    @GetMapping("/{loanId}")
    public Optional<Loan> getLoanById(@PathVariable String loanId) {
        // Loan loan = loanRepository.findByLoanId(loanId);
        // if (loan == null) {
        // throw new RuntimeException("Loan not found with id: " + loanId);
        // }
        // return loan;
        return this.loanRepository.findByLoanId(loanId);
    }
}
