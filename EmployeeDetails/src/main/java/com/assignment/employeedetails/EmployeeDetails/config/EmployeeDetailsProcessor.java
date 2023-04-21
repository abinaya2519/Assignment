package com.assignment.employeedetails.EmployeeDetails.config;

import org.springframework.batch.item.ItemProcessor;

import com.assignment.employeedetails.EmployeeDetails.entity.EmployeeDetails;

public class EmployeeDetailsProcessor implements ItemProcessor<EmployeeDetails, EmployeeDetails> {

	@Override
	public EmployeeDetails process(EmployeeDetails item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
