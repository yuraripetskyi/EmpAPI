package employees.test.com.dao.impl;

import employees.test.com.dao.DepartmentDAO;
import employees.test.com.entity.Department;
import employees.test.com.mappers.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentDAOImpl implements DepartmentDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DepartmentMapper departmentMapper;

    private static final String GET_ALL_QUERY = "select * from tblDepartments";

    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, departmentMapper);
    }

    @PostConstruct
    public void fillDefaultData(){
        if(this.findAll().size()==0){
            Resource resource = new ClassPathResource("/scripts/departments.sql");
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        }
    }
}
