package com.asuraiv.batch.job.chunktest;

import com.asuraiv.batch.job.chunktest.vo.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ChunkTestReader implements ItemReader<Person> {

	private List<Person> persons = Arrays.asList(
		Person.builder().username("john").address("new york").build(),
		Person.builder().username("james").address("new york").build(),
		Person.builder().username("hong").address("ichon").build(),
		Person.builder().username("park").address("seoul").build(),
		Person.builder().username("lee").address("tokyo").build(),
		Person.builder().username("thomas").address("LA").build(),
		Person.builder().username("richard").address("washington").build(),
		Person.builder().username("mark").address("boston").build(),
		Person.builder().username("luke").address("san diego").build(),
		Person.builder().username("sam").address("california").build()
	);

	private int idx = 0;

	@Override
	public Person read() {

		if(idx < persons.size()) {
			return persons.get(idx++);
		}

		return null;
	}
}
