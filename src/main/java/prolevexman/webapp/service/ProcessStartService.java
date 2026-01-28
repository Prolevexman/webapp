package prolevexman.webapp.service;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

import java.util.UUID;

@Service
public class ProcessStartService {
    private final ProcessStrategyFactory processStrategyFactory;
    private final ProcessInstanceDao processInstanceDao;

    public ProcessStartService(ProcessStrategyFactory processStrategyFactory, ProcessInstanceDao processInstanceDao) {
        this.processStrategyFactory = processStrategyFactory;
        this.processInstanceDao = processInstanceDao;
    }

    public UUID startProcess(String ip, String inputData, ProcessType type) {
        ProcessInstance processInstance = new ProcessInstance(ip, inputData);
        processInstanceDao.save(processInstance);

        ProcessExecutionStrategy strategy = processStrategyFactory.getStrategy(type);
        strategy.execute(processInstance);

        return processInstance.getId();
    }
}
