package prolevexman.webapp.dao.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("inmemory")
public class InMemoryProcessInstanceDao implements ProcessInstanceDao {

    private final Map<Long, ProcessInstance> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public void save(ProcessInstance processInstance) {
        if (processInstance.getId() == null) {
            long id = idSequence.getAndIncrement();
            processInstance.setId(id);
        }
        storage.put(processInstance.getId(), processInstance);
    }

    @Override
    public void update(ProcessInstance processInstance) {
        storage.put(processInstance.getId(), processInstance);
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public List<ProcessInstance> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public ProcessInstance findById(Long id) {
        return storage.get(id);
    }
}
