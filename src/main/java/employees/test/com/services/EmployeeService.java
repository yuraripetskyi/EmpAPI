package employees.test.com.services;

import employees.test.com.dto.RegisterEmployeeDTO;
import employees.test.com.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee create(RegisterEmployeeDTO registerEmployeeDTO);

    Employee update(Employee employee);

    int delete(Integer id);
}
