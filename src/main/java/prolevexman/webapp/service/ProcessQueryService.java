package prolevexman.webapp.service;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.List;
import java.util.UUID;

@Service
public class ProcessQueryService {
    private final ProcessInstanceDao processInstanceDao;

    public ProcessQueryService(ProcessInstanceDao processInstanceDao) {
        this.processInstanceDao = processInstanceDao;
    }

    public ProcessInstance getProcess(UUID id) {
        ProcessInstance processInstance = processInstanceDao.findById(id);

        if(processInstance == null) {
            throw new IllegalArgumentException("Процесс не найден, id: " +id);
        }

        return processInstance;
    }

    public List<ProcessInstance> getAllProcesses() {
        return processInstanceDao.findAll();
    }

}
