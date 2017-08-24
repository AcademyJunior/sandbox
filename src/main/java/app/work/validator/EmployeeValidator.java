package app.work.validator;

import app.work.domain.Employee;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static app.work.validator.WorkErrorKeys.*;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors error) {
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", EMPTY_NAME);
        Employee employee = (Employee) object;
        if (employee.getHourlyWage() < 0) {
            error.reject(NEGATIVE_HOURLY_WAGE);
        }
        if (employee.getTaxRate() < 0) {
            error.reject(NEGATIVE_TAX_RATE);
        } else if (employee.getTaxRate() >= 1) {
            error.reject(TOO_BIG_TAX_RATE);
        }
    }
}

