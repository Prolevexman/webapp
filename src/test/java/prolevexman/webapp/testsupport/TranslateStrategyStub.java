package prolevexman.webapp.testsupport;

import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

import java.util.concurrent.CompletableFuture;

public class TranslateStrategyStub implements ProcessExecutionStrategy {

    @Override
    public ProcessType getType() {
        return ProcessType.TRANSLATE;
    }

    @Override
    public CompletableFuture<String> execute(String input) {
        return CompletableFuture.completedFuture("translated");
    }
}
