package com.asuraiv.batch.job.chunktest;

import com.asuraiv.batch.job.chunktest.mapper.PersonMapper;
import com.asuraiv.batch.job.chunktest.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional // chunk-size는 DB 트랜잭션과 관련없다. 명시적으로 트랜잭션 관리를 해줘야함.
	public void write(List<? extends Person> items) {
		log.info("# chunk start");
		for (Person person : items) {
			if(saveCnt == 7) {
				throw new RuntimeException("Error occur!");
			}
			personMapper.create(person);
			log.info("# {} save complete. {}", saveCnt++, person);
		}
	}
}
