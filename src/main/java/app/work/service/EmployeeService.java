package app.work.service;

import app.work.dao.EmployeeDao;
import app.work.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Optional<Employee> getEmployee(long id) {
        return employeeDao.getEmployee(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getEmployees();
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    public void updateEmployee(long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);
    }

    public boolean isEmployeePresent(long id) {
        return employeeDao.getEmployee(id).isPresent();
    }
}
