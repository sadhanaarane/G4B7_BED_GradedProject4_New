package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gl.entity.Employee;
import com.gl.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	@PreAuthorize("permitAll()")
	public List<Employee> getAllEmployees() {

		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/getEmployeeById")
	@PreAuthorize("permitAll()")
	public Employee getEmployeeById(Long id) {

		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/employees/sort")
	@PreAuthorize("permitAll()")
	public List<Employee> getEmployeesSortedByFirstName(Direction dir) {

		return employeeService.getEmployeesSortedByFirstName(dir);
	}

	@PostMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee newEmployee(@RequestBody Employee employee) {

		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee updateEmployee(@RequestBody Employee employee) {

		// get employee from database by id
		Long id = employee.getId();
		Employee existingEmployee = employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		// save updated employee object
		return employeeService.updateEmployee(existingEmployee);
	}

	@DeleteMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteEmployee(Long id) {

		employeeService.deleteEmployeeById(id);
		return "Employee deleted successfully";
	}

	@GetMapping("/employees/search")
	@PreAuthorize("permitAll()")
	public List<Employee> searchEmployee(String firstName) {

		return employeeService.searchBy(firstName);
	}
}
