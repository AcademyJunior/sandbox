package app.task;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Task task=(Task) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"taskName","taskName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"taskDescription","taskDescription.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"estimateTime","estimateTime.required");
        if(task.getEstimateTime()<=0){
            errors.rejectValue("estimateTime","negativeValue",new Object[]{"'estimateTime'"},"estimateTime can't be <=0");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"estimatePrice","estimatePrice.required");
        if(task.getEstimatePrice()<=0){
            errors.rejectValue("estimatePrice","funnyValye",new Object[]{"'estimatePrice'"},"it means that you ll pay us for this task");
        }
    }
}
