package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.model.Loan;
import com.empresa.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanCorntroller {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping
	public List<Loan> getAllLoan(){
		return loanService.getAllLoan();
	}
	
	@PostMapping Loan createLoan(@RequestBody Loan loan) {
		return loanService.createLoan(loan);
	}
	
}
