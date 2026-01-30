package prolevexman.webapp.service.strategy;

import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.enums.ProcessType;

import java.util.concurrent.CompletableFuture;

public interface ProcessExecutionStrategy {

    CompletableFuture<String> execute(String input);
    ProcessType getType();
}
