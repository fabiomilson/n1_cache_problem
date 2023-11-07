package com.cache.n1.problem.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cache.n1.problem.enums.StrategyType;
import com.cache.n1.problem.service.BaseService;

@Component
public class StrategyTypeConfig {


    @Bean
    public Map<StrategyType, BaseService> generateStrategys(final List<BaseService> services) {
        return services.stream()
            .collect(Collectors.toMap(
                BaseService::getStrategyType,
                service -> service
            ));
    }

}
