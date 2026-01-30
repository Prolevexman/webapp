package prolevexman.webapp.dao;

import prolevexman.webapp.model.entity.ProcessTranslateExecution;

import java.util.List;
import java.util.UUID;

public interface ProcessTranslateExecutionDao {

    void save(ProcessTranslateExecution processExecution);
    void deleteByInstanceId(Long instanceId);
    List<ProcessTranslateExecution> findAll();
}
