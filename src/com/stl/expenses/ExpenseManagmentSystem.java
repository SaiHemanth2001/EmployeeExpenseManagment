package com.stl.expenses;

import java.time.LocalDate;
import java.util.Scanner;

import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.Employees;
import com.stl.expenses.domain.ExpenseClaim;
import com.stl.expenses.exceptions.EmployeeNotFoundException;
import com.stl.expenses.managment.ExpenseManagmentProcess;
import com.stl.expenses.managment.ExpressExpenseManagmentProcess;
import com.stl.expenses.managment.RegularExpenseManagmentProcess;
import com.stl.expenses.ui.UIFunctions;
import com.stl.expenses.utilities.ExpenseAnalysis;
import com.stl.expenses.utilities.ExpenseAnalysisTemp;

public class ExpenseManagmentSystem {

	public static void main(String[] args) {
		Employees employees = new Employees();
		UIFunctions uiFunctions = new UIFunctions();
		Scanner scanner = new Scanner(System.in);

		ExpenseManagmentProcess expressEMP = new ExpressExpenseManagmentProcess();
		ExpenseManagmentProcess regularEMP = new RegularExpenseManagmentProcess();

		boolean readyToExit = false;

		while (!readyToExit) {
			System.out.println("Expense Managment System");
			System.out.println("------------------------");
			System.out.println("e - register a new employee");
			System.out.println("c - register a new claim");
			System.out.println("p - print all employees");
			System.out.println("a - approve claim");
			System.out.println("r1 - outstanding expense claims");
			System.out.println("r2 - paid expense claims");
			System.out.println("r3 - expense claims above a specified amount");
			System.out.println("x - exit");

			String option = scanner.nextLine();

			ExpenseAnalysis expenseAnalysis = new ExpenseAnalysisTemp();

			switch (option) {
			case "e":
				Employee e = uiFunctions.registerNewEmployee();
				employees.addEmployee(e);
				break;
			case "c":
				ExpenseClaim claim = uiFunctions.registerNewExpenseClaim();
				try {
					employees.addExpenseClaim(claim);
					expressEMP.registerExpenseClaim(claim);
					int id = regularEMP.registerExpenseClaim(claim);
					System.out.println("The claim has be registered with id " + id);
				} catch (EmployeeNotFoundException e1) {
					System.out.println("no Employee with id " + claim.getEmployeeId());
				}
				break;
			case "p":
				employees.printEmployee();
				break;
			case "x":
				readyToExit = true;
				break;
			case "a":
				System.out.println("Enter the claim Id");
				int claimId = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Enter the employee Id");
				int employeeId = scanner.nextInt();
				scanner.nextLine();

				Employee foundEmployee = employees.findById(employeeId);

				System.out.println("Enter r for regular or e for express");
				String claimType = scanner.nextLine();

				ExpenseManagmentProcess requestedProcess;
				if (claimType.equals("r")) {
					requestedProcess = regularEMP;
				} else {

					requestedProcess = expressEMP;
				}
				boolean result = requestedProcess.approveClaim(claimId, foundEmployee);
				System.out.println("the result is " + result);

				break;
			case "r1":
				expenseAnalysis.OutstandingClaims();
				break;
			case "r2":
				System.out.println("Enter date from");
				String dateFrom = scanner.nextLine();
				System.out.println("Enter date to");
				String dateTo = scanner.nextLine();
				expenseAnalysis.paidExpenseClaims(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
				break;
			case "r3":
				System.out.println("Enter amount");
				Double amount = scanner.nextDouble();
				scanner.nextLine();
				expenseAnalysis.printClaimsOverAmount(amount);
				break;
			default:
				System.out.println("Option not valid");
			}
		}

	}

}
