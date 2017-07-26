package app.hero;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Arrays;

import static app.hero.ErrorKeys.*;

@Component
public class HeroValidator implements org.springframework.validation.Validator {

    private static final int MAX_STAT = 5;
    private static final int MIN_STAT = 0;
    private static final int MAX_STATS_SUM = 10;

    public boolean supports(Class clazz) {
        return Hero.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error) {
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", EMPTY_NAME);
        Hero hero = (Hero) obj;
        int[] stats = {hero.getAgility(), hero.getIntelligence(), hero.getStrength()};
        if (Arrays.stream(stats).sum() > MAX_STATS_SUM) {
            error.reject(STATS_SUM_TOO_BIG);
        }
        for (int stat : stats) {
            if (stat > MAX_STAT) {
                error.reject(STAT_TOO_BIG);
            } else if (stat < MIN_STAT) {
                error.reject(STAT_TOO_SMALL);
            }
        }
    }
}

