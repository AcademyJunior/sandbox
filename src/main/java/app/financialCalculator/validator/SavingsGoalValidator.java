package app.financialCalculator.validator;

import app.financialCalculator.model.SavingsGoal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SavingsGoalValidator implements Validator {
    private static final String NEGATIVE_VALUE = "NEGATIVE_VALUE";
    private static final String REQUIRED_FIELD = "REQUIRED_FIELD";

    @Override
    public boolean supports(Class aClass) {
        return SavingsGoal.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SavingsGoal savingsGoal = (SavingsGoal) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "savingsGoalName", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "savingsGoalValue", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "savingsGoalUserId", REQUIRED_FIELD);
        if (savingsGoal.getSavingsGoalValue() <= 0) {
            errors.rejectValue("savingsGoalValue", NEGATIVE_VALUE);
        }
    }
}
