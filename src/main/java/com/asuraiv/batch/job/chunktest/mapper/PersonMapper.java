package com.asuraiv.batch.job.chunktest.mapper;

import com.asuraiv.batch.job.chunktest.vo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonMapper {

	void create(@Param("person")Person person);
}
