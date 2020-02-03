package employees.test.com.mappers;

import employees.test.com.entity.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setDpID(resultSet.getInt("depID"));
        department.setDpName(resultSet.getString("depName"));
        return department;
    }
}
