package com.asuraiv.batch.job.sample.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SampleDto {

	private int value;

	public SampleDto(int value) {
		this.value = value;
	}
}
