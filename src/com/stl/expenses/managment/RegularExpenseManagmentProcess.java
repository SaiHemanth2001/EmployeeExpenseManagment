package com.stl.expenses.managment;

import java.util.ArrayList;
import java.util.List;

import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.ExpenseClaim;
import com.stl.expenses.domain.StaffEmployee;

public class RegularExpenseManagmentProcess implements ExpenseManagmentProcess {

	List<ExpenseClaim> claims = new ArrayList<>();

	@Override
	public int registerExpenseClaim(ExpenseClaim claim) {
		// TODO Auto-generated method stub
		claims.add(claim);
		return claims.size() - 1;
	}

	@Override
	public boolean approveClaim(int id, Employee employee) {
		// TODO Auto-generated method stub
		ExpenseClaim claim = claims.get(id);
		if (claim.getTotalAmount() < 100) {
			return true;
		}
		if (employee instanceof StaffEmployee) {
			return true;
		}
		return false;
	}

}
