package prolevexman.webapp.testsupport;

import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

import java.util.concurrent.CompletableFuture;

public class ModifyStrategyStub implements ProcessExecutionStrategy {
    @Override
    public ProcessType getType() {
        return ProcessType.MODIFY;
    }

    @Override
    public CompletableFuture<String> execute(String input) {
        return CompletableFuture.completedFuture("modified");
    }
}
