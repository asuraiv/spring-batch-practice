package com.asuraiv.batch.job.sample;

import com.asuraiv.batch.job.sample.dto.SampleDto;
import com.asuraiv.batch.job.sample.provider.SampleDtoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@StepScope
@Component
public class SampleItemReader implements ItemReader<SampleDto> {

	@Autowired
	private SampleDtoProvider sampleDtoProvider;

	@Value("#{jobParameters['itemLimit']}")
	private int itemLimit;

	private List<SampleDto> items = new ArrayList<>();

	private int index = 0;

	@Override
	public SampleDto read() {

		if(items.isEmpty()) {
			log.info("Read sample dto list. limit: {}", itemLimit);
			items = sampleDtoProvider.getSampleDtoList(itemLimit);
		}

		if(items.size() > index) {
			return items.get(index++);
		}

		return null; // null 이 반환되면 reader 종료
	}
}
