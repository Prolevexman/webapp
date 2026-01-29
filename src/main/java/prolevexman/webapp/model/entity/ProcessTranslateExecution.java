package prolevexman.webapp.model.entity;

import java.util.UUID;

public class ProcessTranslateExecution {

    private UUID id;
    private UUID instanceId;

    public ProcessTranslateExecution(UUID instanceId) {
        this.id = UUID.randomUUID();
        this.instanceId = instanceId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getInstanceId() {
        return instanceId;
    }
}
