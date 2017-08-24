package app.work.dao;

import app.work.domain.Employee;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.WORK_EMPLOYEES;


@Repository
public class EmployeeDao {

    @Autowired
    private DSLContext dslContext;

    public List<Employee> getEmployees() {
        return dslContext.selectFrom(WORK_EMPLOYEES).fetchInto(Employee.class);
    }

    public Optional<Employee> getEmployee(long id) {
        return dslContext.selectFrom(WORK_EMPLOYEES)
                .where(WORK_EMPLOYEES.ID.eq(id))
                .fetchOptionalInto(Employee.class);
    }

    public void addEmployee(Employee employee) {
        dslContext.insertInto(WORK_EMPLOYEES, WORK_EMPLOYEES.NAME, WORK_EMPLOYEES.HOURLY_WAGE, WORK_EMPLOYEES.TAX_RATE)
                .values(employee.getName(), employee.getHourlyWage(), employee.getTaxRate())
                .execute();
    }


    public void deleteEmployee(long id) {
        dslContext.deleteFrom(WORK_EMPLOYEES)
                .where(WORK_EMPLOYEES.ID.eq(id))
                .execute();
    }

    public void updateEmployee(long id, Employee employee) {
        dslContext.update(WORK_EMPLOYEES)
                .set(WORK_EMPLOYEES.NAME, employee.getName())
                .set(WORK_EMPLOYEES.HOURLY_WAGE, employee.getHourlyWage())
                .set(WORK_EMPLOYEES.TAX_RATE, employee.getTaxRate())
                .where(WORK_EMPLOYEES.ID.eq(id))
                .execute();
    }

}
