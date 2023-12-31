package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.ResorsseNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	
	// get Employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	 //Create Employee Rest API
	@PostMapping("/employees")
	public Employee createEmplyee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
//	Get Employee By id Rest API
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResorsseNotFoundException("Employee Not exit with id:" + id));
		return ResponseEntity.ok(employee);
	}
	// Update Employee Rest API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResorsseNotFoundException("Employee Not exit with id:" + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// Delete Rest API
	@DeleteMapping("/employees/{id}")
	public ResponseEntity <Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResorsseNotFoundException("Employee Not exit with id:" + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response  =  new HashMap <>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}

