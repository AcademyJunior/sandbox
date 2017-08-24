package app.financialCalculator.validator;

import app.financialCalculator.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private static final String NEGATIVE_VALUE = "NEGATIVE_VALUE";
    private static final String REQUIRED_FIELD = "REQUIRED_FIELD";
    private static final String WRONG_WORD_LENGTH = "WRONG_WORD_LENGTH";

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", REQUIRED_FIELD);
        if (user.getUserJob() != null) {
            if (user.getUserJob().length() < 2) {
                errors.rejectValue("userJob", WRONG_WORD_LENGTH);
            }
        }
        if (user.getUserEarnings() < 0) {
            errors.rejectValue("userEarnings", NEGATIVE_VALUE);
        }
        if (user.getUserSavings() < 0) {
            errors.rejectValue("userSavings", NEGATIVE_VALUE);
        }
    }
}
