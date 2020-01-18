package com.asuraiv.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@EnableBatchProcessing
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBatchPracticeApplication extends DefaultBatchConfigurer {

	@Override
	public void setDataSource(@Qualifier("transitDataSource") DataSource dataSource) {
		// override to do not set datasource even if a datasource exist.
		// initialize will use a Map based JobRepository (instead of database)
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchPracticeApplication.class, args);
	}
}
