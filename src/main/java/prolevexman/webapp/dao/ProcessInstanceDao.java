package prolevexman.webapp.dao;

import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.List;
import java.util.UUID;

public interface ProcessInstanceDao {

    void save(ProcessInstance processInstance);
    void update(ProcessInstance processInstance);
    void deleteById(UUID id);
    List<ProcessInstance> findAll();
    ProcessInstance findById(UUID id);
}
