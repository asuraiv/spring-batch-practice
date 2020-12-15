package com.asuraiv.batch.job.chunktest;

import com.asuraiv.batch.job.chunktest.mapper.PersonMapper;
import com.asuraiv.batch.job.chunktest.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChunkTestWriter implements ItemWriter<Person> {

	private PersonMapper personMapper;
	private int saveCnt = 1;

	public ChunkTestWriter(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	@Override
	public void write(List<? extends Person> items) {
		log.info("# chunk start");
		for (Person person : items) {
			if(saveCnt == 7) {
				// Transaction 테스트
				throw new RuntimeException("Error occur!");
			}
			personMapper.create(person);
			log.info("# {} save complete. {}", saveCnt++, person);
		}
	}
}
