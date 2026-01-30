package prolevexman.webapp.dao.postgres;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;

import java.util.List;

@Repository
@Profile("postgres")
public class PostgresProcessTranslateExecutionDao implements ProcessTranslateExecutionDao {
    private final JdbcTemplate jdbcTemplate;

    public PostgresProcessTranslateExecutionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ProcessTranslateExecution processTranslateExecution) {
        String sql = """
            INSERT INTO process_translate_execution (instance_id)
            VALUES (?)
            RETURNING id
            """;

        Long id = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                processTranslateExecution.getInstanceId()
        );

        processTranslateExecution.setId(id);
    }

    @Override
    public void deleteByInstanceId(Long instanceId) {
        jdbcTemplate.update(
                "DELETE FROM process_translate_execution WHERE instance_id = ?",
                instanceId
        );
    }

    @Override
    public List<ProcessTranslateExecution> findAll() {
        String sql = "SELECT id, instance_id FROM process_translate_execution";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProcessTranslateExecution e =
                    new ProcessTranslateExecution(rs.getObject("instance_id", Long.class));
            e.setId(rs.getObject("id", Long.class));
            return e;
        });
    }
}
