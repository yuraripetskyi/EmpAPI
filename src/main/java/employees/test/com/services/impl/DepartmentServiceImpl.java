package employees.test.com.services.impl;

import employees.test.com.dao.DepartmentDAO;
import employees.test.com.entity.Department;
import employees.test.com.enums.EDepartment;
import employees.test.com.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    @Override
    public List<Department> getDepartments() {
        return departmentDAO.findAll();
    }

    @Override
    public Map<Integer, String> getIdDepartmentMap() {
        List<Department> departments = departmentDAO.findAll();
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::getDpID,
                        Department::getDpName
                ));
    }

}
