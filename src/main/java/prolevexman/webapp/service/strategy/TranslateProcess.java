package prolevexman.webapp.service.strategy;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.external.MyMemoryApiClient;

@Service
public class TranslateProcess implements ProcessExecutionStrategy{

    private final ProcessInstanceDao processInstanceDao;
    private final ProcessTranslateExecutionDao processTranslateExecutionDao;
    private final MyMemoryApiClient translateApiClient;

    public TranslateProcess(ProcessInstanceDao processInstanceDao, ProcessTranslateExecutionDao processTranslateExecutionDao, MyMemoryApiClient translateApiClient) {
        this.processInstanceDao = processInstanceDao;
        this.processTranslateExecutionDao = processTranslateExecutionDao;
        this.translateApiClient = translateApiClient;
    }

    @Override
    public ProcessType getType() {
        return ProcessType.TRANSLATE;
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        ProcessTranslateExecution processTranslateExecution = new ProcessTranslateExecution(processInstance.getId());

        processTranslateExecutionDao.save(processTranslateExecution);

        translateApiClient.translate(processInstance.getInputData())
                .thenAccept(result -> {
                    processInstance.setResult(result);
                    processInstanceDao.update(processInstance);
                })
                .exceptionally(ex -> {
                    processInstance.setResult(ex.getMessage());
                    processInstanceDao.update(processInstance);
                    return null;
                });
    }
}
