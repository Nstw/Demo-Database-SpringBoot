package com.hw.database.controller;

import com.hw.database.dto.LoanDTO;
import com.hw.database.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.createLoan(loanDTO));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable String loanId) {
        return loanService.getLoanByLoanId(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<LoanDTO>> getLoansByPerson(@PathVariable String personId) {
        return ResponseEntity.ok(loanService.getLoansByPersonId(personId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanDTO>> getLoansByStatus(@PathVariable String status) {
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }
}