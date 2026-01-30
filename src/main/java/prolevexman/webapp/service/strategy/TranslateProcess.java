package prolevexman.webapp.service.strategy;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.external.MyMemoryApiClient;

import java.util.concurrent.CompletableFuture;

@Service
public class TranslateProcess implements ProcessExecutionStrategy{

    private final MyMemoryApiClient translateApiClient;

    public TranslateProcess(MyMemoryApiClient translateApiClient) {
        this.translateApiClient = translateApiClient;
    }

    @Override
    public ProcessType getType() {
        return ProcessType.TRANSLATE;
    }

    @Override
    public CompletableFuture<String> execute(String input) {

        return translateApiClient.translate(input);
    }
}
