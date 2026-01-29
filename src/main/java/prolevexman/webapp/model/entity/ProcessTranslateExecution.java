package prolevexman.webapp.model.entity;

import java.util.UUID;

public class ProcessTranslateExecution {

    private UUID id;
    private UUID instanceId;

    public ProcessTranslateExecution(UUID instanceId) {
        this.instanceId = instanceId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInstanceId() {
        return instanceId;
    }
}
