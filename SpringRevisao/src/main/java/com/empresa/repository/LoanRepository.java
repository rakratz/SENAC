package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
