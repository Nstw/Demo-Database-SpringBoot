package com.hw.database.service;

import com.hw.database.dto.LoanDTO;
import com.hw.database.entity.Loan;
import com.hw.database.entity.Person;
import com.hw.database.repository.LoanRepository;
import com.hw.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public LoanDTO createLoan(LoanDTO loanDTO) {
        Person person = personRepository.findByPersonId(loanDTO.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + loanDTO.getPersonId()));

        Loan loan = new Loan();
        loan.setLoanId(loanDTO.getLoanId());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setLoanTerm(loanDTO.getLoanTerm());
        loan.setStatus(loanDTO.getStatus());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setPerson(person);

        Loan savedLoan = loanRepository.save(loan);
        return convertToDTO(savedLoan);
    }

    public Optional<LoanDTO> getLoanByLoanId(String loanId) {
        return loanRepository.findByLoanId(loanId)
                .map(this::convertToDTO);
    }

    public List<LoanDTO> getLoansByPersonId(String personId) {
        return loanRepository.findByPerson_PersonId(personId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LoanDTO convertToDTO(Loan loan) {
        if (loan == null) {
            throw new RuntimeException("Loan cannot be null");
        }

        LoanDTO dto = new LoanDTO();
        dto.setLoanId(loan.getLoanId());

        // Safely handle Person relationship
        if (loan.getPerson() != null) {
            dto.setPersonId(loan.getPerson().getPersonId());
        } else {
            throw new RuntimeException("Person not found for loan: " + loan.getLoanId());
        }

        dto.setLoanAmount(loan.getLoanAmount());
        dto.setLoanTerm(loan.getLoanTerm());
        dto.setStatus(loan.getStatus());
        dto.setInterestRate(loan.getInterestRate());
        return dto;
    }
}