package prolevexman.webapp.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProcessInstance {

    private UUID id;
    private LocalDateTime startTime;
    private String initiatorIp;
    private String inputData;
    private String result;

    public ProcessInstance(String initiatorIp, String inputData) {
        this.id = UUID.randomUUID();
        this.startTime = LocalDateTime.now();
        this.initiatorIp = initiatorIp;
        this.inputData = inputData;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getInitiatorIp() {
        return initiatorIp;
    }

    public String getInputData() {
        return inputData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
