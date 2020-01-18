package com.asuraiv.batch.job.sample.config;

import com.asuraiv.batch.job.sample.SampleItemProcessor;
import com.asuraiv.batch.job.sample.SampleItemReader;
import com.asuraiv.batch.job.sample.SampleItemWriter;
import com.asuraiv.batch.job.sample.SampleTasklet;
import com.asuraiv.batch.job.sample.dto.SampleDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SampleItemReader sampleItemReader;

	@Autowired
	private SampleItemProcessor sampleItemProcessor;

	@Autowired
	private SampleItemWriter sampleItemWriter;

	@Autowired
	private SampleTasklet sampleTasklet;

	@Bean
	public Job sampleJob() {
		return this.jobBuilderFactory.get("sampleJob")
			.start(sampleStep())
			.next(sampleTaskletStep())
			.build();
	}

	private Step sampleStep() {
		return this.stepBuilderFactory.get("sampleStep")
			.<SampleDto, String>chunk(5)
			.reader(sampleItemReader)
			.processor(sampleItemProcessor)
			.writer(sampleItemWriter)
			.build();
	}

	private Step sampleTaskletStep() {
		return this.stepBuilderFactory.get("sampleTaskletStep")
			.tasklet(sampleTasklet)
			.build();
	}
}
