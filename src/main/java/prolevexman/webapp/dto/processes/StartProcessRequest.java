package prolevexman.webapp.dto.processes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import prolevexman.webapp.model.enums.ProcessType;

public class StartProcessRequest {
    @NotNull
    private ProcessType type;
    @NotBlank
    private String inputData;

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
}
