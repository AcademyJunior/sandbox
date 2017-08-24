package app.financialCalculator.validator;


import app.financialCalculator.model.Expense;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ExpenseValidator implements Validator {
    private static final String NEGATIVE_VALUE = "NEGATIVE_VALUE";
    private static final String REQUIRED_FIELD = "REQUIRED_FIELD";

    @Override
    public boolean supports(Class aClass) {
        return Expense.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Expense expense = (Expense) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseName", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseValue", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseDate", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseUserId", REQUIRED_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expenseTypeId", REQUIRED_FIELD);
        if (expense.getExpenseValue() <= 0) {
            errors.rejectValue("expenseValue", NEGATIVE_VALUE);
        }
    }
}
