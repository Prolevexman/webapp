package prolevexman.webapp.model.entity;

import prolevexman.webapp.model.enums.ProcessType;

import java.util.UUID;

public class ProcessExecution {

    private ProcessType processType;
    private UUID instanceId;

    public ProcessExecution(ProcessType processType, UUID instanceId) {
        this.processType = processType;
        this.instanceId = instanceId;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public UUID getInstanceId() {
        return instanceId;
    }
}
