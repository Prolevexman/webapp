package prolevexman.webapp.dao;

import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.List;

public interface ProcessInstanceDao {

    void save(ProcessInstance processInstance);
    void update(ProcessInstance processInstance);
    void deleteById(Long id);
    List<ProcessInstance> findAll();
    ProcessInstance findById(Long id);
}
