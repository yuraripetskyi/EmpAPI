package employees.test.com.controller;

import employees.test.com.dto.RegisterEmployeeDTO;
import employees.test.com.entity.Employee;
import employees.test.com.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.debug("Getting all Employees");
        return employeeService.getEmployees();
    }

    @PostMapping
    public Employee register(
            @RequestBody RegisterEmployeeDTO employeeDTO
    ) {
        log.debug("Creating Employee with name: {}", employeeDTO.getName());
        return employeeService.create(employeeDTO);
    }

    @PutMapping
    public Employee update(
            @RequestBody Employee employee
    ) {
        log.debug("Updating Employee with id: {}", employee.getId());
        return employeeService.update(employee);
    }

    @DeleteMapping(path = "/{id}")
    public int delete(
            @PathVariable Integer id
    ) {
        log.debug("Deleting Employee with id: {}", id);
        return employeeService.delete(id);
    }
}
