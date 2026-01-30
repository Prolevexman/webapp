package prolevexman.webapp.service;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

@Service
public class ProcessStartService {
    private final ProcessStrategyFactory processStrategyFactory;
    private final ProcessInstanceDao processInstanceDao;
    private final ProcessTranslateExecutionDao processTranslateExecutionDao;

    public ProcessStartService(ProcessStrategyFactory processStrategyFactory, ProcessInstanceDao processInstanceDao, ProcessTranslateExecutionDao processTranslateExecutionDao) {
        this.processStrategyFactory = processStrategyFactory;
        this.processInstanceDao = processInstanceDao;
        this.processTranslateExecutionDao = processTranslateExecutionDao;
    }

    public Long startProcess(String ip, String inputData, ProcessType type) {
        ProcessInstance processInstance = new ProcessInstance(ip, inputData);
        processInstanceDao.save(processInstance);

        if(type == ProcessType.TRANSLATE) {
            ProcessTranslateExecution processTranslateExecution = new ProcessTranslateExecution(processInstance.getId());
            processTranslateExecutionDao.save(processTranslateExecution);
        }

        ProcessExecutionStrategy strategy = processStrategyFactory.getStrategy(type);
        strategy.execute(inputData)
                .thenAccept(result -> {
                    processInstance.setResult(result);
                    processInstanceDao.update(processInstance);
                })
                .exceptionally(ex -> {
                    processInstance.setResult("Ошибка: " + ex.getMessage());
                    processInstanceDao.update(processInstance);
                    return null;
                });

        return processInstance.getId();
    }
}
