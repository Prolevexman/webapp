package prolevexman.webapp.service.strategy;

import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.enums.ProcessType;

public interface ProcessExecutionStrategy {

    void execute(ProcessInstance processInstance);
    ProcessType getType();
}
