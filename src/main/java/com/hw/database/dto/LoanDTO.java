package com.hw.database.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LoanDTO {
    private String loanId;
    private String personId;
    private BigDecimal loanAmount;
    private Integer loanTerm;
    private String status;
    private BigDecimal interestRate;
}