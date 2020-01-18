package com.asuraiv.batch.job.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SampleItemWriter implements ItemWriter<String>, StepExecutionListener {

	private int totalWrittenCount = 0;

	@Override
	public void write(List<? extends String> items) {

		for (String item : items) {
			log.info("Current item: {}", item);
			this.totalWrittenCount++;
		}

		log.info("Complete writing a chunk.");
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// ignore
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		// 다음 Step으로 특정 파라메터를 넘긴다
		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		executionContext.put("totalWrittenCount", this.totalWrittenCount);

		stepExecution.getJobExecution().setExecutionContext(executionContext);

		return ExitStatus.COMPLETED;
	}
}
