package prolevexman.webapp.service;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.UUID;

@Service
public class ProcessDeleteService {
    private ProcessInstanceDao processInstanceDao;
    private ProcessTranslateExecutionDao processTranslateExecutionDao;

    public ProcessDeleteService(ProcessInstanceDao processInstanceDao, ProcessTranslateExecutionDao processTranslateExecutionDao) {
        this.processInstanceDao = processInstanceDao;
        this.processTranslateExecutionDao = processTranslateExecutionDao;
    }

    public void deleteProcess(Long id) {
        ProcessInstance processInstance = processInstanceDao.findById(id);

        if(processInstance == null) {
            throw new IllegalArgumentException("Процесс не найден, id: " + id);
        }
        processTranslateExecutionDao.deleteByInstanceId(id);
        processInstanceDao.deleteById(id);

    }
}
