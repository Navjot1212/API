package com.NavjotSingh.AssignmentWeek25;

import java.sql.*;

public class JdbcAssignment {

	public static void main(String[] args) throws SQLException {
		ResultSet resultSet;
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "Navjot13");
		Statement statement = connection.createStatement();
		// Write a single query to select max and min salary from an employee table?
		String maxMinSalaryQuery = "SELECT MAX(salary),MIN(salary) FROM employees";
		resultSet = statement.executeQuery(maxMinSalaryQuery);
		while (resultSet.next()) {
			System.out.println("Maximum Salary is "+resultSet.getString(1));
			System.out.println("Minimum Salary is "+resultSet.getString(2));
		}
		//Write a query to return second highest salary from employee table. 
		String secondHighestSalaryQuery = "SELECT MAX(salary)FROM Employees WHERE Salary < (SELECT MAX(Salary)FROM Employees)";
		resultSet = statement.executeQuery(secondHighestSalaryQuery);
		while (resultSet.next()) {
			System.out.println("Second Highest Salary is "+resultSet.getString(1));
		}
		connection.close();
	}
}
