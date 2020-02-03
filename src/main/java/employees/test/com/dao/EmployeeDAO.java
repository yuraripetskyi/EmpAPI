package employees.test.com.dao;

import employees.test.com.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(Integer id);

    int save(Employee employee);

    int delete(Integer id);
}
