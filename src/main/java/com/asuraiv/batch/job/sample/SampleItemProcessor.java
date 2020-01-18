package com.asuraiv.batch.job.sample;

import com.asuraiv.batch.job.sample.dto.SampleDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SampleItemProcessor implements ItemProcessor<SampleDto, String> {

	@Override
	public String process(SampleDto item) {
		return String.valueOf(item.getValue());
	}
}
