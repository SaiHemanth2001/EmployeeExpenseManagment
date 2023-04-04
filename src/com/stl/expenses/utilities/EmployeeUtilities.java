package com.stl.expenses.utilities;

import com.stl.expenses.domain.Employee;
import com.stl.expenses.domain.Employees;
import com.stl.expenses.exceptions.InvalidEmployeeIdException;
import com.stl.expenses.exceptions.NameTooShortException;

public class EmployeeUtilities {

	public boolean employeeExists(Employees employees, Employee employee) {
		return employees.findBySurName(employee.getSurName()) != null;
	}

	public Integer validateEmployeeId(String inputId) throws InvalidEmployeeIdException {
		try {
			Integer id = Integer.valueOf(inputId);
			return id;
		} catch (NumberFormatException e) {
			throw new InvalidEmployeeIdException();
		}
	}

	public void validateEmployeeName(String firstName, String surName) throws NameTooShortException  {
		Integer length = firstName.length() + surName.length();
		if (length < 6) {
			throw new NameTooShortException();
		}
	}

}
