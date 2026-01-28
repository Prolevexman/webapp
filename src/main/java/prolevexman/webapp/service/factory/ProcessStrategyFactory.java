package prolevexman.webapp.service.factory;

import org.springframework.stereotype.Component;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessStrategyFactory {
    private final Map<ProcessType, ProcessExecutionStrategy> strategies;

    public ProcessStrategyFactory(List<ProcessExecutionStrategy> strategyList) {
        strategies = new HashMap<>();
        for(ProcessExecutionStrategy strategy : strategyList) {
            strategies.put(strategy.getType(), strategy);
        }
    }

    public ProcessExecutionStrategy getStrategy(ProcessType type) {
        ProcessExecutionStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Стратегия для данного типа не найдена: " + type);
        }
        return strategy;
    }
}
