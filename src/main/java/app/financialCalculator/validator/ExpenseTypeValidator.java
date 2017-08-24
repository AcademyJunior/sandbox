package app.financialCalculator.validator;

import app.financialCalculator.model.ExpenseType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ExpenseTypeValidator implements Validator {
    private static final String REQUIRED_FIELD = "REQUIRED_FIELD";

    @Override
    public boolean supports(Class aClass) {
        return ExpenseType.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseTypeName", REQUIRED_FIELD);
    }
}
