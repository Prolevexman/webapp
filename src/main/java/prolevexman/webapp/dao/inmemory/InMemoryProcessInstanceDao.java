package prolevexman.webapp.dao.inmemory;

import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProcessInstanceDao implements ProcessInstanceDao {

    private final Map<UUID, ProcessInstance> storage = new ConcurrentHashMap<>();

    @Override
    public void save(ProcessInstance processInstance) {
        if (processInstance.getId() == null) {
            processInstance.setId(UUID.randomUUID());
        }
        storage.put(processInstance.getId(), processInstance);
    }

    @Override
    public void update(ProcessInstance processInstance) {
        storage.put(processInstance.getId(), processInstance);
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }

    @Override
    public List<ProcessInstance> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public ProcessInstance findById(UUID id) {
        return storage.get(id);
    }
}
