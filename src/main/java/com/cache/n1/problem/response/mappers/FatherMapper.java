package com.cache.n1.problem.response.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.response.FatherResponse;

@Mapper(componentModel = "spring")
public interface FatherMapper {

    List<FatherResponse> toListResponse(final List<Father> content);
}
