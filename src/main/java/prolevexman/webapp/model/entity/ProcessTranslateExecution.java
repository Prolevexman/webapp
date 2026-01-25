package prolevexman.webapp.model.entity;

import prolevexman.webapp.model.enums.ProcessType;

import java.util.UUID;

public class ProcessTranslateExecution {

    private UUID id;
    private ProcessType processType;
    private UUID instanceId;

    public ProcessTranslateExecution(ProcessType processType, UUID instanceId) {
        this.id = UUID.randomUUID();
        this.processType = processType;
        this.instanceId = instanceId;
    }

    public UUID getId() {
        return id;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public UUID getInstanceId() {
        return instanceId;
    }
}
