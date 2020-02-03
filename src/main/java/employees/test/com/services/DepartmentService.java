package employees.test.com.services;

import employees.test.com.entity.Department;
import employees.test.com.enums.EDepartment;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Department> getDepartments();

    Map<Integer, String> getIdDepartmentMap();
}
