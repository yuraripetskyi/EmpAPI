package employees.test.com.dao;

import employees.test.com.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();
}
