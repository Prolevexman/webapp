package prolevexman.webapp.mapper;

import prolevexman.webapp.dto.processes.GetProcessResponse;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.List;

public class ProcessMapper {
    public static GetProcessResponse toDto(ProcessInstance instance) {
        GetProcessResponse dto = new GetProcessResponse();
        dto.setId(instance.getId());
        dto.setStartTime(instance.getStartTime());
        dto.setIp(instance.getInitiatorIp());
        dto.setInputData(instance.getInputData());
        dto.setResult(instance.getResult());
        return dto;
    }

    public static List<GetProcessResponse> toDtoList(List<ProcessInstance> instances) {
        return instances.stream()
                .map(ProcessMapper::toDto)
                .toList();
    }
}
