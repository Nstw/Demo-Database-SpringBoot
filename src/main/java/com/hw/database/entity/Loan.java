package com.hw.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "loan")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", unique = true)
    private String loanId;

    @Column(name = "loan_amount")
    private BigDecimal loanAmount;

    @Column(name = "loan_term")
    private Integer loanTerm;

    @Column(name = "status")
    private String status;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}