package com.assignment.employeedetails.EmployeeDetails.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jobs")
public class EmployeeDetailsController {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@PostMapping("/importEmployees")
	public void importCsvToDb(@RequestParam("file") MultipartFile multiPartFile) throws IOException{
		
	String fileName=multiPartFile.getOriginalFilename();
		
		
		JobParameters jobParameters=new JobParametersBuilder().addString("fullPathFileName",fileName).
				addLong("startat",System.currentTimeMillis()).toJobParameters();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			
			e.printStackTrace();
		} catch (JobRestartException e) {
			
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			
			e.printStackTrace();
		}
	}
	
}
