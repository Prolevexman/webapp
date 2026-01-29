package prolevexman.webapp.dao.inmemory;

import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProcessTranslateExecutionDao implements ProcessTranslateExecutionDao {
    private final Map<UUID, ProcessTranslateExecution> storage = new ConcurrentHashMap<>();

    @Override
    public void save(ProcessTranslateExecution processTranslateExecution) {
        storage.put(processTranslateExecution.getId(), processTranslateExecution);
    }

    @Override
    public void deleteByInstanceId(UUID instanceId) {
        storage.entrySet().removeIf(entry -> entry.getValue().getInstanceId().equals(instanceId));
    }

    @Override
    public List<ProcessTranslateExecution> findAll() {
        return new ArrayList<>(storage.values());
    }
}
