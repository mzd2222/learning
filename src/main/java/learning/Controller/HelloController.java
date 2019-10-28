package learning.Controller;


import learning.Bean.Employee;
import learning.Mapper.EmployeeMapper;
import learning.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee user(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee) {
        Employee employee1 = employeeService.updateEmployee(employee);
        return employee1;
    }

    @GetMapping("/del/{id}")
    public String deletEmp(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName")
    public Employee getEmpBylastName(String lastName) {
        return employeeService.getEmpBylastName(lastName);
    }

}
