package com.cache.n1.problem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.exception.InvalidRequestException;
import com.cache.n1.problem.repository.FatherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class L2Service implements BaseService {

    @Value("${spring.jpa.properties.hibernate.cache.use_second_level_cache}")
    private boolean isUseSecondLevelCache;

    @Value("${spring.jpa.properties.hibernate.cache.use_query_cache}")
    private boolean isUseQueryCache;

    private final FatherRepository repository;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.L2;
    }

    @Override
    public List<Father> getContent(final int size) throws InvalidRequestException {

        if (!isUseSecondLevelCache || !isUseQueryCache) {
            throw new InvalidRequestException("Endpoint não deve ser usado com as flags de second level desabilitadas.");
        }

        return repository.findAll(Pageable.ofSize(size)).getContent();
    }
}
