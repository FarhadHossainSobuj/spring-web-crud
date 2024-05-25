package com.example.spring_boot_web_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring_boot_web_crud.model.Employee;
import com.example.spring_boot_web_crud.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String getAllEmployees(Model model) {
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}
	
	@GetMapping("/new")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping
	public String createEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditEmployeeForm(@PathVariable Long id, Model model) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		
		if(employee.isPresent()) {
			model.addAttribute("employee", employee.get());
			return "edit_employee";
		} else {
			return "redirect:/employees";
		}
	}
	
	@PostMapping("/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employeeDetails) {
		//TODO: process POST request
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		
		if(employee.isPresent()) {
			Employee existingEmployee = employee.get();
			existingEmployee.setName(employeeDetails.getName());
			existingEmployee.setRole(employeeDetails.getRole());
			employeeService.saveEmployee(existingEmployee);
			return "redirect:/employees";
		} else {
			return "redirect:/employees";
		}
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
	
	
}
