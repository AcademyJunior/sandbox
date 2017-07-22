package app.hero_maker;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Arrays;

@Component
public class DataValidator implements org.springframework.validation.Validator {

    public boolean supports(Class clazz) {
        return Hero.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error) {
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", "Name is empty.");
        Hero hero = (Hero) obj;
        int[] stats = {hero.getAgility(), hero.getIntelligence(), hero.getStrength()};
        if (Arrays.stream(stats).sum() > 10) {
            error.reject("sumTooBig","Sum of stats can not be bigger than 10.");
        }
        for (int i : stats) {
            if (i > 5) {
                error.reject("statTooBig","A statistic can not be bigger than 5.");
            } else if (i < 0) {
                error.reject("statTooSmall", "A statistic can not be a negative value.");
            }
        }
    }
}

