package com.assignment.employeedetails.EmployeeDetails.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.assignment.employeedetails.EmployeeDetails.entity.EmployeeDetails;
import com.assignment.employeedetails.EmployeeDetails.repository.EmployeeDetailsRepository;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDetails> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) throws IOException {
		FlatFileItemReader<EmployeeDetails> itemReader=new FlatFileItemReader<EmployeeDetails>();
		byte[] a = pathToFile.getBytes();
		InputStream targetStream = new ByteArrayInputStream(a);
		itemReader.setResource(new InputStreamResource(targetStream));
		itemReader.setName("csvreader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
		
	
	}
	



	private LineMapper<EmployeeDetails> lineMapper() {
		DefaultLineMapper<EmployeeDetails> lineMapper=new DefaultLineMapper<EmployeeDetails>();
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		
		delimitedLineTokenizer.setNames("id","employeeName","firstName","lastName","projectName","emailAddress","contact","country","channel");
		
		BeanWrapperFieldSetMapper<EmployeeDetails> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<EmployeeDetails>();
		beanWrapperFieldSetMapper.setTargetType(EmployeeDetails.class);
		
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return lineMapper;
	}

	@Bean
	public EmployeeDetailsProcessor processor() {
		return new EmployeeDetailsProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<EmployeeDetails> writer(){
		RepositoryItemWriter<EmployeeDetails> itemWriter= new RepositoryItemWriter<EmployeeDetails>();
		itemWriter.setRepository(employeeDetailsRepository);
		itemWriter.setMethodName("save");
		return itemWriter;
	}
	
	@Bean
	public Step csvStep(JobRepository jobRepository, JpaTransactionManager transactionManager,
			FlatFileItemReader<EmployeeDetails> reader) {
		return new StepBuilder("csv-step", jobRepository)
				.<EmployeeDetails, EmployeeDetails>chunk(10, transactionManager)
				.reader(reader)
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	@Autowired
	public Job csvJob(JobRepository jobRepository, Step csvStep) {
	    return new JobBuilder("csv-job", jobRepository).flow(csvStep).end().build();            
	}
	
	
}
