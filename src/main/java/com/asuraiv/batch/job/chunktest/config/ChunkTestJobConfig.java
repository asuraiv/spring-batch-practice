package com.asuraiv.batch.job.chunktest.config;

import com.asuraiv.batch.job.chunktest.ChunkTestProcessor;
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
import org.springframework.transaction.PlatformTransactionManager;

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

	@Autowired
	private ChunkTestProcessor chunkTestProcessor;

	@Autowired
	private PlatformTransactionManager transactionManager;

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
			.processor(chunkTestProcessor)
			.writer(chunkTestWriter)
			.transactionManager(transactionManager) // chunk 단위로 트랜잭션 생성
			.build();
	}
}
