package com.gl.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.gl.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Employee saveEmployee(Employee theEmployee);

	public Employee getEmployeeById(Long id);

	public Employee updateEmployee(Employee theEmployee);

	public String deleteEmployeeById(Long empId);

	public List<Employee> searchBy(String firstName);

	public List<Employee> getEmployeesSortedByFirstName(Direction direction);
}
