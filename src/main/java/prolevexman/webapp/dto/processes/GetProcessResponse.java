package prolevexman.webapp.dto.processes;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetProcessResponse {
    private UUID id;
    private LocalDateTime startTime;
    private String initiatorIp;
    private String inputData;
    private String result;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getInitiatorIp() {
        return initiatorIp;
    }

    public void setIp(String initiatorIp) {
        this.initiatorIp = initiatorIp;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
