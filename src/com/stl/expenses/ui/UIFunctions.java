package com.stl.expenses.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.stl.expenses.domain.Department;
import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.ExpenseClaim;
import com.stl.expenses.domain.ExpenseItem;
import com.stl.expenses.domain.ExpenseType;
import com.stl.expenses.domain.StaffEmployee;
import com.stl.expenses.exceptions.InvalidEmployeeIdException;
import com.stl.expenses.exceptions.NameTooShortException;
import com.stl.expenses.utilities.EmployeeUtilities;

public class UIFunctions {

	public Employee registerNewEmployee() {
		Scanner scanner = new Scanner(System.in);
		Employee employee = new Employee();
		EmployeeUtilities employeeUtilities = new EmployeeUtilities();
		
		boolean idIsValid = false;
		while (!idIsValid) {
			System.out.println("Enter the id");
			String inputId = scanner.nextLine();

			try {
				Integer id = employeeUtilities.validateEmployeeId(inputId);
				employee.setId(id);
				idIsValid = true;
			} catch (InvalidEmployeeIdException e) {
				System.out.println("Id is invalid");
			}
		}
		System.out.println("Enter the Title");
		String title = scanner.nextLine();
		employee.setTitle(title);

		boolean nameIsValid = false;

		while (!nameIsValid) {

			System.out.println("Enter the first name");
			String firstName = scanner.nextLine();
			employee.setFirstName(firstName);

			System.out.println("Enter the surname");
			String surname = scanner.nextLine();
			employee.setSurName(surname);

			try {
				employeeUtilities.validateEmployeeName(firstName, surname);
				nameIsValid = true;
			} catch (NameTooShortException e) {
				System.out.println("name NotValid");
			}

		}

		System.out.println("Enter the Job Title");
		String jobTitle = scanner.nextLine();
		employee.setJobTitle(jobTitle);

		boolean departmentIsValid = false;

		while (!departmentIsValid) {
			System.out.println("Enter the department");
			String department = scanner.nextLine();
			try {
				Department d = Department.valueOf(department.toUpperCase());
				employee.setDepartment(d);
				departmentIsValid = true;
			} catch (IllegalArgumentException e) {
				System.out.println("department not valid");
			}
		}
		System.out.println("Is this a member of staff?Y/N");
		String isAStaffMember = scanner.nextLine();
		if (isAStaffMember.toUpperCase().equals("Y")) {
			StaffEmployee staff = new StaffEmployee(employee);
			System.out.println("Enter the UserName");
			String username = scanner.nextLine();
			staff.setUsername(username);

			System.out.println("Enter the Password");
			String password = scanner.nextLine();
			staff.setPassword(password);

			return staff;

		}

		else  {
			return (employee);
		}
		

	}

	public ExpenseClaim registerNewExpenseClaim() {
		Scanner scanner = new Scanner(System.in);
		EmployeeUtilities employeeUtilities = new EmployeeUtilities();

		System.out.println("Enter the Claim Id");
		int claimId = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter the employee Id");
		int employeeId = scanner.nextInt();
		scanner.nextLine();

		LocalDate dateOfClaim = LocalDate.now();

		ExpenseClaim claim = new ExpenseClaim(claimId, employeeId, dateOfClaim);

		boolean finished = false;
		while (!finished) {
			System.out.println("Enter the Expense Id");
			int eiId = scanner.nextInt();
			scanner.nextLine();
			boolean expenseTypeIsValid = false;
			ExpenseType eiType = null;
			while (!expenseTypeIsValid) {
				System.out.println("Enter the ExpenseType");
				String expenseType = scanner.nextLine();
				try {
					eiType = ExpenseType.valueOf(expenseType.toUpperCase());

					expenseTypeIsValid = true;
				} catch (IllegalArgumentException e) {
					System.out.println("ExpenseType not valid");
				}
			}

			System.out.println("Enter the Description");
			String description = scanner.nextLine();

			System.out.println("Enter the amount");
			double amount = scanner.nextDouble();
			scanner.nextLine();

			ExpenseItem ei = new ExpenseItem(eiId, claimId, eiType, description, amount);

			claim.addExpenseItem(ei);

			System.out.println("Enter another expense item ? (Y/N)");
			String anyMore = scanner.nextLine();

			if (!anyMore.toUpperCase().equals("Y")) {
				finished = true;
			}
		}

		return claim;
	}

}
