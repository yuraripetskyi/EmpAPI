package employees.test.com.services.impl;

import employees.test.com.dao.EmployeeDAO;
import employees.test.com.dto.RegisterEmployeeDTO;
import employees.test.com.entity.Employee;
import employees.test.com.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee create(RegisterEmployeeDTO registerEmployeeDTO) {
        Employee employee = new Employee(
                registerEmployeeDTO.getName(),
                registerEmployeeDTO.getActive(),
                registerEmployeeDTO.getDepId()
        );
        int id = employeeDAO.save(employee);
        employee.setId(id);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        int id = employeeDAO.save(employee);
        return  employeeDAO.findById(id);
    }

    @Override
    public int delete(Integer id) {
        return employeeDAO.delete(id);
    }
}
