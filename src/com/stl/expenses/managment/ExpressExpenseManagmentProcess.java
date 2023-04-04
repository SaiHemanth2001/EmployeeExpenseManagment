package com.stl.expenses.managment;

import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.ExpenseClaim;

public class ExpressExpenseManagmentProcess implements ExpenseManagmentProcess {

	private ExpenseClaim claim;

	@Override
	public int registerExpenseClaim(ExpenseClaim claim) {
		this.claim = claim;
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean approveClaim(int id, Employee employee) {
		// TODO Auto-generated method stub
		return (claim.getTotalAmount() < 50);
	}

}
