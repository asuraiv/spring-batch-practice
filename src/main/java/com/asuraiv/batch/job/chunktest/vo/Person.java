package com.asuraiv.batch.job.chunktest.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Person {
	private String username;
	private String address;
	private String email;
	private String phone;
}
