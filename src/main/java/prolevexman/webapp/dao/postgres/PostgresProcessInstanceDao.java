package prolevexman.webapp.dao.postgres;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.mapper.ProcessInstanceMapper;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.util.List;

@Repository
@Profile("postgres")
public class PostgresProcessInstanceDao implements ProcessInstanceDao {

    private final JdbcTemplate jdbcTemplate;

    public PostgresProcessInstanceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ProcessInstance processInstance) {
        String sql = """
                INSERT INTO process_instance
                (start_time, initiator_ip, input_data, result)
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;
        Long id = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                processInstance.getStartTime(),
                processInstance.getInitiatorIp(),
                processInstance.getInputData(),
                processInstance.getResult()
        );

        processInstance.setId(id);
    }

    @Override
    public void update(ProcessInstance p) {
        String sql = """
            UPDATE process_instance
            SET result = ?
            WHERE id = ?
            """;

        jdbcTemplate.update(
                sql,
                p.getResult(),
                p.getId()
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "DELETE FROM process_instance WHERE id = ?",
                id
        );
    }

    @Override
    public List<ProcessInstance> findAll() {
        String sql = "SELECT * FROM process_instance";
        return jdbcTemplate.query(sql, ProcessInstanceMapper.INSTANCE);
    }

    @Override
    public ProcessInstance findById(Long id) {
        String sql = "SELECT * FROM process_instance WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                ProcessInstanceMapper.INSTANCE,
                id
        );
    }
}
