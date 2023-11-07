package com.cache.n1.problem.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.exception.InvalidRequestException;
import com.cache.n1.problem.repository.FatherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class N1BatchSizeService implements BaseService {

    private final FatherRepository repository;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.N1_BATCH_SIZE;
    }

    @Override
    public List<Father> getContent(final int size) throws InvalidRequestException {

        if (!hasBatchSizeAnnotation()) {
            throw new InvalidRequestException(
                "Necessário configurar @BatchSize na propriedade childrens da entitdade Father.");
        }

        return repository.findAll(PageRequest.ofSize(size)).getContent();
    }

    public boolean hasBatchSizeAnnotation() {
        try {
            return Father.class.getDeclaredField("childrens")
                .isAnnotationPresent(org.hibernate.annotations.BatchSize.class);
        } catch (NoSuchFieldException e) {
            log.error("Falha ao recuperar campo childrens", e);
            return false;
        }
    }

}
