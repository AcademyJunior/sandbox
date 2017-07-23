package app.hero;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import static app.hero.ErrorKeys.*;
import java.util.Arrays;

@Component
public class HeroValidator implements org.springframework.validation.Validator {

    public boolean supports(Class clazz) {
        return Hero.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors error) {
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", EMPTY_NAME);
        Hero hero = (Hero) obj;
        int[] stats = {hero.getAgility(), hero.getIntelligence(), hero.getStrength()};
        if (Arrays.stream(stats).sum() > MAX_STATS_SUM) {
            error.reject(STATS_TOO_BIG);
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

