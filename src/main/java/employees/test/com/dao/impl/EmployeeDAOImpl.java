package employees.test.com.dao.impl;

import employees.test.com.dao.EmployeeDAO;
import employees.test.com.entity.Employee;
import employees.test.com.mappers.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class EmployeeDAOImpl implements EmployeeDAO {
    private static final String GET_ALL_QUERY = "select * from tblEmployees";
    private static final String DELETE_FROM_EMPLOYEE_WHERE_EMP_ID = "delete from tblEmployees where empID = ?";
    private static final String SELECT_FROM_TBL_EMPLOYEES_WHERE_EMP_ID = "select * from tblEmployees where empID = ?";
    private static final String UPDATE_EMPLOYEE_WHERE_EMP_ID = "update tblEmployees set empName = ?, active = ?, depID =? where empID = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final EmployeeMapper employeeMapper;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate, EmployeeMapper employeeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        // init SimpleJdbcInsert
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        this.simpleJdbcInsert.setTableName("tblEmployees");
        this.simpleJdbcInsert.setGeneratedKeyName("empID");
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    public void fillDefaultData(){
        if(this.findAll().size()==0){
            Resource resource = new ClassPathResource("/scripts/employee.sql");
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        }
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, employeeMapper);
    }

    /**
     * @param id - employee id
     * @return null if employee not found
     */
    @Override
    public Employee findById(Integer id) {
        if (isNull(id))
            throw new RuntimeException("Error during deleting employee: Id cant be null");
        try {
            return jdbcTemplate.queryForObject(SELECT_FROM_TBL_EMPLOYEES_WHERE_EMP_ID, new Object[]{id}, employeeMapper);
        } catch (Exception e) {
            log.debug("Can't find user employee id {}", id);
            return null;
        }
    }

    @Override
    public int save(Employee employee) {
        if (nonNull(employee.getId())) {
            update(employee);
            return employee.getId();
        } else {
            return insert(employee);
        }
    }

    /**
     * @param id - employee id
     */
    @Override
    public int delete(Integer id) {
        if (isNull(id))
            throw new RuntimeException("Error during deleting employee: Id cant be null");
        return jdbcTemplate.update(DELETE_FROM_EMPLOYEE_WHERE_EMP_ID, id);
    }

    private int insert(Employee employee) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("empName", employee.getName());
        parameters.put("active", employee.isActive());
        parameters.put("depID", employee.getDepId());
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    private void update(Employee employee) {
        jdbcTemplate.update(
                UPDATE_EMPLOYEE_WHERE_EMP_ID,
                employee.getName(),
                employee.isActive(),
                employee.getDepId(),
                employee.getId()
        );
    }
}
