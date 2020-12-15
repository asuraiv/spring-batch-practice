package com.asuraiv.batch.job.chunktest;

import com.asuraiv.batch.job.chunktest.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChunkTestProcessor implements ItemProcessor<Person, Person> {

	private int processCnt = 1;

	@Override
	public Person process(Person item) {

		log.info("#{} processing item", processCnt++);

		return item;
	}
}
