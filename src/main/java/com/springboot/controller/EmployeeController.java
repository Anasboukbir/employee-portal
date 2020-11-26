package com.springboot.controller;

import java.util.List;
import java.util.Optional;

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

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.*;

import com.springboot.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins="http://elasticbeanstalk-eu-central-1-479844551162.s3-website.eu-central-1.amazonaws.com")
@RequestMapping("/api/v1/")
public class EmployeeController{
	@Autowired
	private EmployeeRepository employeeRepository;
@GetMapping("/getAll")
public List<Employee> getAllEmployees(){
	return employeeRepository.findAll();}

@PostMapping("/employee")
public Employee insertEmployee(@RequestBody  Employee emp){
return this.employeeRepository.save(emp);}

@PutMapping("/employee/{id}")
public ResponseEntity<Employee> updatEmployee(@PathVariable long id,@RequestBody  Employee emp) {

	Employee empupdated=employeeRepository.findById(id)
			       .orElseThrow(()->new ResourceNotFoundException("error entity not found"));
		 
		 
	empupdated.setFirstName(emp.getFirstName());
	empupdated.setLastName(emp.getLastName());
	empupdated.setEmailId(emp.getEmailId());
	
	Employee empnew=employeeRepository.save(empupdated);
 



	
	       
	return ResponseEntity.ok(empnew);
}
@DeleteMapping("/employee/{id}")
public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {

	Employee empdeleted=employeeRepository.findById(id)
			       .orElseThrow(()->new ResourceNotFoundException("error entity not found"));
		 
	
	
	employeeRepository.delete(empdeleted);
 



	
	       
	return ResponseEntity.ok(empdeleted);
}
}
