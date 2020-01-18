package com.asuraiv.batch.job.sample.provider;

import com.asuraiv.batch.job.sample.dto.SampleDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDtoProvider {

	public List<SampleDto> getSampleDtoList(int limit) {

		List<SampleDto> sampleDtoList = new ArrayList<>();

		for(int i = 0; i < limit; i++) {
			sampleDtoList.add(new SampleDto(i + 1));
		}

		return sampleDtoList;
	}
}
