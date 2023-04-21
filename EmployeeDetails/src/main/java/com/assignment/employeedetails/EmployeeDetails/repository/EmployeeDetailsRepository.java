package com.assignment.employeedetails.EmployeeDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.employeedetails.EmployeeDetails.entity.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer> {

}
