package employees.test.com.controller;

import employees.test.com.entity.Department;
import employees.test.com.enums.EDepartment;
import employees.test.com.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {
    private final DepartmentService departmentService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Department> getIdDepartments() {
        return departmentService.getDepartments();
    }

    @RequestMapping(path = "/map", method = RequestMethod.GET)
    public Map<Integer, String> getIdDepartmentMap() {
        return departmentService.getIdDepartmentMap();
    }
}
