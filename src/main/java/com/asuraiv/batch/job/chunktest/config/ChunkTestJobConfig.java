package com.asuraiv.batch.job.chunktest.config;

import com.asuraiv.batch.job.chunktest.ChunkTestReader;
import com.asuraiv.batch.job.chunktest.ChunkTestWriter;
import com.asuraiv.batch.job.chunktest.vo.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChunkTestJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ChunkTestReader chunkTestReader;

	@Autowired
	private ChunkTestWriter chunkTestWriter;

	@Bean
	public Job chunkTestJob() {
		return this.jobBuilderFactory.get("chunkTestJob")
			.start(chunkTestStep())
			.build();
	}

	@Bean
	public Step chunkTestStep() {
		return this.stepBuilderFactory.get("chunkTestStep")
			.<Person, Person>chunk(5)
			.reader(chunkTestReader)
			.writer(chunkTestWriter)
			.build();
	}
}
