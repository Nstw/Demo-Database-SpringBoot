package com.naome.demo.database.loan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "loan_id", length = 50, unique = true)
    private String loanId;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "loan_term")
    private int loanTerm;

    @Column(name = "status")
    private String status;

    @Column(name = "interest_rate")
    private double interestRate;

    /*
     * @Id
     * 
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * private Long id;
     * 
     * @Column(name = "loan_id", unique = true, nullable = false)
     * private String loanId;
     * private String applicantName;
     * private double loanAmount;
     * private int loanTerm;
     * private String status;
     * private double interestRate;
     * 
     * public Loan() {
     * }
     * 
     * public Loan(Long id, String loanId, String applicantName, double loanAmount,
     * int loanTerm, String status,
     * double interestRate) {
     * this.id = id;
     * this.loanId = loanId;
     * this.applicantName = applicantName;
     * this.loanAmount = loanAmount;
     * this.loanTerm = loanTerm;
     * this.status = status;
     * this.interestRate = interestRate;
     * }
     * 
     * public Long getId() {
     * return id;
     * }
     * 
     * public String getLoanId() {
     * return loanId;
     * }
     * 
     * public String getApplicantName() {
     * return applicantName;
     * }
     * 
     * public void setApplicantName(String applicantName) {
     * this.applicantName = applicantName;
     * }
     * 
     * public double getLoanAmount() {
     * return loanAmount;
     * }
     * 
     * public int getLoanTerm() {
     * return loanTerm;
     * }
     * 
     * public void setLoanTerm(int loanTerm) {
     * this.loanTerm = loanTerm;
     * }
     * 
     * public String getStatus() {
     * return status;
     * }
     * 
     * public double getInterestRate() {
     * return interestRate;
     * }
     * 
     * public void setInterestRate(double interestRate) {
     * this.interestRate = interestRate;
     * }
     * 
     * public void setStatus(String status) {
     * this.status = status;
     * }
     * 
     * public void setLoanAmount(double loanAmount) {
     * this.loanAmount = loanAmount;
     * }
     * 
     * public void setId(Long id) {
     * this.id = id;
     * }
     * 
     * public void setLoanId(String loanId) {
     * this.loanId = loanId;
     * }
     * 
     * @Override
     * public String toString() {
     * return "Loan{" +
     * "id=" + id +
     * ", loanId='" + loanId + '\'' +
     * ", applicantName='" + applicantName + '\'' +
     * ", loanAmount=" + loanAmount +
     * ", loanTerm=" + loanTerm +
     * ", status='" + status + '\'' +
     * ", interestRate=" + interestRate +
     * '}';
     * }
     */
}
