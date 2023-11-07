package com.cache.n1.problem.service;

import java.util.List;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.exception.InvalidRequestException;

public interface BaseService {

    StrategyType getStrategyType();

    List<Father> getContent(final int size) throws InvalidRequestException;

}
