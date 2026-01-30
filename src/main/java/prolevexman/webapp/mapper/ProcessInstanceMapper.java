package prolevexman.webapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import prolevexman.webapp.model.entity.ProcessInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessInstanceMapper implements RowMapper<ProcessInstance> {

    public static final ProcessInstanceMapper INSTANCE = new ProcessInstanceMapper();

    @Override
    public ProcessInstance mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProcessInstance processInstance = new ProcessInstance(
                rs.getString("initiator_ip"),
                rs.getString("input_data")
        );
        processInstance.setId(rs.getLong("id"));
        processInstance.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        processInstance.setResult(rs.getString("result"));
        return processInstance;
    }
}
