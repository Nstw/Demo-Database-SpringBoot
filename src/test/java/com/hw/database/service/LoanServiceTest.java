package com.hw.database.service;

import com.hw.database.dto.LoanDTO;
import com.hw.database.entity.Loan;
import com.hw.database.entity.Person;
import com.hw.database.repository.LoanRepository;
import com.hw.database.repository.PersonRepository;
import com.hw.database.service.LoanService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLoan_Success() {
        // Arrange
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanId("L001");
        loanDTO.setPersonId("P001");
        loanDTO.setLoanAmount(new BigDecimal("10000"));
        loanDTO.setLoanTerm(12);
        loanDTO.setStatus("ACTIVE");
        loanDTO.setInterestRate(new BigDecimal("5.5"));

        Person person = new Person();
        person.setPersonId("P001");
        person.setName("John Doe");

        Loan loan = new Loan();
        loan.setLoanId("L001");
        loan.setPerson(person);
        loan.setLoanAmount(new BigDecimal("10000"));
        loan.setLoanTerm(12);
        loan.setStatus("ACTIVE");
        loan.setInterestRate(new BigDecimal("5.5"));

        when(personRepository.findByPersonId("P001")).thenReturn(Optional.of(person));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // Act
        LoanDTO result = loanService.createLoan(loanDTO);

        // Assert
        assertNotNull(result);
        assertEquals("L001", result.getLoanId());
        assertEquals("P001", result.getPersonId());
        assertEquals(new BigDecimal("10000"), result.getLoanAmount());
        assertEquals(12, result.getLoanTerm());
        assertEquals("ACTIVE", result.getStatus());
        assertEquals(new BigDecimal("5.5"), result.getInterestRate());

        verify(personRepository).findByPersonId("P001");
        verify(loanRepository).save(any(Loan.class));
    }

    @Test
    void createLoan_PersonNotFound() {
        // Arrange
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setPersonId("P001");

        when(personRepository.findByPersonId("P001")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            loanService.createLoan(loanDTO);
        });

        assertEquals("Person not found with ID: P001", exception.getMessage());
        verify(personRepository).findByPersonId("P001");
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    void getLoanByLoanId_Success() {
        // Arrange
        Person person = new Person();
        person.setPersonId("P001");
        person.setName("John Doe");

        Loan loan = new Loan();
        loan.setLoanId("L001");
        loan.setPerson(person);
        loan.setLoanAmount(new BigDecimal("10000"));
        loan.setLoanTerm(12);
        loan.setStatus("ACTIVE");
        loan.setInterestRate(new BigDecimal("5.5"));

        when(loanRepository.findByLoanId("L001")).thenReturn(Optional.of(loan));

        // Act
        Optional<LoanDTO> result = loanService.getLoanByLoanId("L001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("L001", result.get().getLoanId());
        assertEquals("P001", result.get().getPersonId());
        assertEquals(new BigDecimal("10000"), result.get().getLoanAmount());
        assertEquals(12, result.get().getLoanTerm());
        assertEquals("ACTIVE", result.get().getStatus());
        assertEquals(new BigDecimal("5.5"), result.get().getInterestRate());

        verify(loanRepository).findByLoanId("L001");
    }

    @Test
    void getLoanByLoanId_NotFound() {
        // Arrange
        when(loanRepository.findByLoanId("L001")).thenReturn(Optional.empty());

        // Act
        Optional<LoanDTO> result = loanService.getLoanByLoanId("L001");

        // Assert
        assertTrue(result.isEmpty());
        verify(loanRepository).findByLoanId("L001");
    }

    @Test
    void getLoansByStatus_Success() {
        // Arrange
        Person person1 = new Person();
        person1.setPersonId("P001");
        person1.setName("John Doe");

        Person person2 = new Person();
        person2.setPersonId("P002");
        person2.setName("Jane Doe");

        Loan loan1 = new Loan();
        loan1.setLoanId("L001");
        loan1.setStatus("ACTIVE");
        loan1.setPerson(person1);
        loan1.setLoanAmount(new BigDecimal("10000"));
        loan1.setLoanTerm(12);
        loan1.setInterestRate(new BigDecimal("5.5"));

        Loan loan2 = new Loan();
        loan2.setLoanId("L002");
        loan2.setStatus("ACTIVE");
        loan2.setPerson(person2);
        loan2.setLoanAmount(new BigDecimal("20000"));
        loan2.setLoanTerm(24);
        loan2.setInterestRate(new BigDecimal("6.0"));

        when(loanRepository.findByStatus("ACTIVE")).thenReturn(Arrays.asList(loan1, loan2));

        // Act
        List<LoanDTO> result = loanService.getLoansByStatus("ACTIVE");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("L001", result.get(0).getLoanId());
        assertEquals("P001", result.get(0).getPersonId());
        assertEquals("L002", result.get(1).getLoanId());
        assertEquals("P002", result.get(1).getPersonId());

        verify(loanRepository).findByStatus("ACTIVE");
    }

    @Test
    void getAllLoans_Success() {
        // Arrange
        Person person1 = new Person();
        person1.setPersonId("P001");
        person1.setName("John Doe");

        Person person2 = new Person();
        person2.setPersonId("P002");
        person2.setName("Jane Doe");

        Loan loan1 = new Loan();
        loan1.setLoanId("L001");
        loan1.setPerson(person1);
        loan1.setStatus("ACTIVE");

        Loan loan2 = new Loan();
        loan2.setLoanId("L002");
        loan2.setPerson(person2);
        loan2.setStatus("PENDING");

        when(loanRepository.findAll()).thenReturn(Arrays.asList(loan1, loan2));

        // Act
        List<LoanDTO> result = loanService.getAllLoans();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("L001", result.get(0).getLoanId());
        assertEquals("P001", result.get(0).getPersonId());
        assertEquals("L002", result.get(1).getLoanId());
        assertEquals("P002", result.get(1).getPersonId());

        verify(loanRepository).findAll();
    }
}