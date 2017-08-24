package app.work.controller;

import app.work.domain.Employee;
import app.work.service.EmployeeService;
import app.work.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/work/employee/")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(employeeValidator);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<Employee>(employeeService.getEmployee(employeeId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void addEmployee(@RequestBody @Valid Employee employee) {
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable long employeeId, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
    }
}
