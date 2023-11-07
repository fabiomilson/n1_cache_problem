package com.cache.n1.problem.controller;

import static com.cache.n1.problem.enums.StrategyType.L1;
import static com.cache.n1.problem.enums.StrategyType.L2;
import static com.cache.n1.problem.enums.StrategyType.N1_BATCH_SIZE;
import static com.cache.n1.problem.enums.StrategyType.N1_FETCH_JOIN;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cache.n1.problem.entity.Father;
import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.exception.InvalidRequestException;
import com.cache.n1.problem.response.FatherResponse;
import com.cache.n1.problem.response.mappers.FatherMapper;
import com.cache.n1.problem.service.BaseService;
import com.cache.n1.problem.util.Clock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fathers")
@RequiredArgsConstructor
public class FatherController {

    private final FatherMapper fatherMapper;
    private final Map<StrategyType, BaseService> strategys;

    @GetMapping("/l1")
    public List<FatherResponse> fetchFathersWithL1Cache(@RequestParam final int size) throws InvalidRequestException {
        return getFathers(size, L1);
    }

    @GetMapping("/l2")
    public List<FatherResponse> fetchFathersWithL2Cache(@RequestParam final int size) throws InvalidRequestException {
        return getFathers(size, L2);
    }

    @GetMapping("/n1-fetch-join")
    public List<FatherResponse> fetchFathersWithN1ProblemFetchJoinSolution(@RequestParam final int size)
        throws InvalidRequestException {
        return getFathers(size, N1_FETCH_JOIN);
    }

    @GetMapping("/n1-batch-size")
    public List<FatherResponse> fetchFathersWithN1ProblemBatchSizeSolution(@RequestParam final int size)
        throws InvalidRequestException {
        return getFathers(size, N1_BATCH_SIZE);
    }

    private List<FatherResponse> getFathers(final int size, final StrategyType strategyType)
        throws InvalidRequestException {
        final var clock = new Clock();
        final List<Father> content = strategys.get(strategyType).getContent(size);
        for (var i = 0; i < content.size(); i++) {
            if (content.get(i).existsChildrens()) { // forçar a chamada do N+1
                // doNothing
            }
            log.info(Integer.toString(i));
        }

        clock.printElapsedTime();
        return fatherMapper.toListResponse(content);
    }

}
