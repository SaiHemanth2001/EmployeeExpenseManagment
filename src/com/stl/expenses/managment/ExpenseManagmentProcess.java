package com.stl.expenses.managment;

import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.ExpenseClaim;

public interface ExpenseManagmentProcess {

	public int registerExpenseClaim(ExpenseClaim claim);

	public boolean approveClaim(int id, Employee employee);

}
