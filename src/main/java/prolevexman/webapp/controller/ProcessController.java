package prolevexman.webapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prolevexman.webapp.dto.processes.GetProcessResponse;
import prolevexman.webapp.dto.processes.GetProcessResultResponse;
import prolevexman.webapp.dto.processes.StartProcessRequest;
import prolevexman.webapp.dto.processes.StartProcessResponse;
import prolevexman.webapp.mapper.ProcessMapper;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.service.ProcessDeleteService;
import prolevexman.webapp.service.ProcessQueryService;
import prolevexman.webapp.service.ProcessStartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/process")
public class ProcessController {
    private final ProcessStartService processStartService;
    private final ProcessQueryService processQueryService;
    private final ProcessDeleteService processDeleteService;

    public ProcessController(ProcessStartService processStartService, ProcessQueryService processQueryService, ProcessDeleteService processDeleteService) {
        this.processStartService = processStartService;
        this.processQueryService = processQueryService;
        this.processDeleteService = processDeleteService;
    }

    @PostMapping("/start")
    public ResponseEntity<StartProcessResponse> startProcess(
            @Valid @RequestBody StartProcessRequest request,
            @RequestHeader(value = "X-Forwarded-For", required = false) String xForwardedFor,
            HttpServletRequest httpServletRequest
    ) {
        String ip;
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            ip = xForwardedFor.split(",")[0].trim();
        } else {
            ip = httpServletRequest.getRemoteAddr();
        }

        Long id = processStartService.startProcess(ip, request.getInputData(), request.getType());
        StartProcessResponse response = new StartProcessResponse();
        response.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<GetProcessResultResponse> getProcessResult(@PathVariable Long id) {
        ProcessInstance processInstance = processQueryService.getProcess(id);
        String result = processInstance.getResult();
        GetProcessResultResponse response = new GetProcessResultResponse();
        response.setResult(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetProcessResponse>> getAllProcesses() {
        List<ProcessInstance> instances = processQueryService.getAllProcesses();
        List<GetProcessResponse> processes = ProcessMapper.toDtoList(instances);
        return ResponseEntity.ok(processes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processDeleteService.deleteProcess(id);
        return ResponseEntity.noContent().build();
    }
}
