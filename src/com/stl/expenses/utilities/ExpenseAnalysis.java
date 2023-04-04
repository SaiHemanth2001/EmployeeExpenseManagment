package com.stl.expenses.utilities;

import java.time.LocalDate;

public interface ExpenseAnalysis {

	public void OutstandingClaims();

	public void paidExpenseClaims(LocalDate from, LocalDate to);

	public void printClaimsOverAmount(Double amount);

}
