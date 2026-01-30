package prolevexman.webapp.dao.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("inmemory")
public class InMemoryProcessTranslateExecutionDao implements ProcessTranslateExecutionDao {
    private final Map<Long, ProcessTranslateExecution> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public void save(ProcessTranslateExecution processTranslateExecution) {
        if (processTranslateExecution.getId() == null) {
            long id = idSequence.getAndIncrement();
            processTranslateExecution.setId(id);
        }
        storage.put(processTranslateExecution.getId(), processTranslateExecution);
    }

    @Override
    public void deleteByInstanceId(Long instanceId) {
        storage.entrySet().removeIf(entry -> entry.getValue().getInstanceId().equals(instanceId));
    }

    @Override
    public List<ProcessTranslateExecution> findAll() {
        return new ArrayList<>(storage.values());
    }
}
