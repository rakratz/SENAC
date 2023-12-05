package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.model.Loan;
import com.empresa.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	public Loan createLoan(Loan loan) {
		return loanRepository.save(loan);
	}
	
	public List<Loan> getAllLoan(){
		return loanRepository.findAll();
	}
}
