package com.stl.expenses;

import java.time.LocalDate;

import com.stl.expenses.domain.Department;
import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.Employees;
import com.stl.expenses.domain.ExpenseClaim;
import com.stl.expenses.domain.ExpenseItem;
import com.stl.expenses.domain.ExpenseType;

public class Main {

	public static void main(String[] args) {
		Employee employee1 = new Employee();
		employee1.setId(1);
		employee1.setTitle("Mr");
		employee1.setFirstName("Miller");
		employee1.setSurName("babylon");
		System.out.println(employee1.getMailingName());
		System.out.println(employee1.getMailingName(true));
		System.out.println(employee1.getMailingName(false));
		Employee employee2 = new Employee(2, "Manager");
		
		Employees employees = new Employees();
		employees.addEmployee(employee1);
		employees.addEmployee(employee2);
		employees.addEmployee(new Employee(22, "mr", "barney", "stinson", "please", Department.FINANCE));
		employee2.setTitle("mr");
		employee2.setFirstName("ted");
		employee2.setSurName("mosby");
		employees.printEmployee();
		Employee foundEmployee = employees.findBySurName("mosby");
		System.out.println("found" + " " + foundEmployee.getMailingName());

		Employee foundEmployee2 = employees.findBySurName("mos");
		System.out.println("didn't find" + " " + (foundEmployee2 == null));

		ExpenseClaim expenseClaim = new ExpenseClaim(2, 24, LocalDate.now());
		System.out.println(expenseClaim.getEmployeeId());
		expenseClaim.setPaid(true);
		System.out.println(expenseClaim.getPaid());
		expenseClaim.setApproved(true);
		expenseClaim.setPaid(true);
		System.out.println(expenseClaim.getPaid());

		ExpenseItem expenseItem = new ExpenseItem(23, 234, ExpenseType.ACCOMODATION, "5star", 67.3);
		System.out.println(expenseItem.getAmount());

		System.out.println(employee1.toString());

		Employee employee3 = new Employee();
		employee3.setId(1);
		employee3.setTitle("Mr");
		employee3.setFirstName("Miller");
		employee3.setSurName("babylon");
		System.out.println(employee1.equals(employee3));
	}

}
