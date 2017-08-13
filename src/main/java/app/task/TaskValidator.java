package app.task;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    private static final String NEGATIVE_VALUE = "NEGATIVE_VALUE";
    private static final String REQUIRED_FIELD = "REQUIRED_FIELD";

    @Override
    public boolean supports(Class clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Task task = (Task) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskName", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskDescription", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimateTime", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimatePrice", REQUIRED_FIELD);
        if (task.getEstimateTime() <= 0) {
            errors.rejectValue("estimateTime", NEGATIVE_VALUE);
        }
        if (task.getEstimatePrice() <= 0) {
            errors.rejectValue("estimatePrice", NEGATIVE_VALUE);
        }
    }
}
