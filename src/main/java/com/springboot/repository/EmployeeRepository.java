package com.springboot.repository;
import  org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.springboot.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}