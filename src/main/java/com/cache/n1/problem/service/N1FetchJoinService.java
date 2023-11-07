package com.cache.n1.problem.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.repository.FatherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class N1FetchJoinService implements BaseService {

    private final FatherRepository repository;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.N1_FETCH_JOIN;
    }

    @Override
    public List<Father> getContent(final int size) {
        return repository.fetchAll(PageRequest.ofSize(size));
    }
}
