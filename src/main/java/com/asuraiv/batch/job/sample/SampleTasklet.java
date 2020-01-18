package com.asuraiv.batch.job.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

		int totalWrittenCount = (int) contribution.getStepExecution().getJobExecution().getExecutionContext().get("totalWrittenCount");

		log.info("Total Written item count. {}", totalWrittenCount);

		return RepeatStatus.FINISHED;
	}
}
