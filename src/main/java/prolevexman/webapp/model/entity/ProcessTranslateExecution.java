package prolevexman.webapp.model.entity;

import java.util.UUID;

public class ProcessTranslateExecution {

    private Long id;
    private Long instanceId;

    public ProcessTranslateExecution(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstanceId() {
        return instanceId;
    }
}
