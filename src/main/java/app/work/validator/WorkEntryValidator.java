package app.work.validator;

import app.work.domain.WorkEntry;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static app.work.validator.WorkErrorKeys.*;

@Component
public class WorkEntryValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return WorkEntry.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors error) {
        WorkEntry workEntry = (WorkEntry) object;
        if (workEntry.getEmployeeId() < 0) {
            error.reject(NEGATIVE_EMPLOYEE_ID);
        }
        if (workEntry.getStartTime().isAfter(workEntry.getFinishTime())) {
            error.reject(START_TIME_AFTER_FINISH_TIME);
        }
        if (workEntry.getStartTime().isEqual(workEntry.getFinishTime())) {
            error.reject(START_TIME_EQUAL_FINISH_TIME);
        }
    }
}