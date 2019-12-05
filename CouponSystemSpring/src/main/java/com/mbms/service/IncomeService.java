package com.mbms.service;

import java.util.List;

import com.mbms.model.Income;

public interface IncomeService {

	Income storeIncome(Income income);

	List<Income> allIncome();

	List<Income> viewIncomeByCompany(long companyId) throws Exception;

	List<Income> viewIncomeByCustomer(long customerId)throws Exception;
}
