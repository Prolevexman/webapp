package prolevexman.webapp.service;

import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.UUID;

public class ProcessDeleteService {
    private ProcessInstanceDao processInstanceDao;
    private ProcessTranslateExecutionDao processTranslateExecutionDao;

    public ProcessDeleteService(ProcessInstanceDao processInstanceDao, ProcessTranslateExecutionDao processTranslateExecutionDao) {
        this.processInstanceDao = processInstanceDao;
        this.processTranslateExecutionDao = processTranslateExecutionDao;
    }

    public void deleteProcess(UUID id) {
        ProcessInstance processInstance = processInstanceDao.findById(id);

        if(processInstance == null) {
            throw new IllegalArgumentException("Процесс не найден, id: " + id);
        }
        processInstanceDao.deleteById(id);
        processTranslateExecutionDao.deleteByInstanceId(id);
    }
}
