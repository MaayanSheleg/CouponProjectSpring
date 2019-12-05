package com.mbms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.model.Income;
import com.mbms.service.IncomeService;

@RestController
@RequestMapping("/Income")
public class IncomeController {
	
	@Resource
	public IncomeService incomeService;
	
	@PostMapping("/createIncome")
	public ResponseEntity<Income> storeIncome(@RequestBody Income income){
		Income income2 = incomeService.storeIncome(income);
		ResponseEntity<Income> result = new ResponseEntity<Income>(income2, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/allIncome")
	public ResponseEntity<List<Income>> allIncome(){
		ResponseEntity<List<Income>> allIncome = new ResponseEntity<List<Income>>(incomeService.allIncome(), HttpStatus.OK);
		return allIncome;
	}

}
