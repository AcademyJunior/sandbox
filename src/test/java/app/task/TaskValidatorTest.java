package app.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static app.task.TaskValidator.NEGATIVE_VALUE;
import static app.task.TaskValidator.REQUIRED_FIELD;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorTest {

    private static String TASK_NAME = "task";
    private static String TASK_DESCRIPTION = "task description";
    private static int ESTIMATE_TIME = 1;
    private static int ESTIMATE_PRICE = 1;
    private static int NEGATIVE_ESTIMATE_TIME = -1;
    private static int NEGATIVE_ESTIMATE_PRICE = -1;


    private Errors errors;
    private Task task;

    @InjectMocks
    private TaskValidator taskValidator;

    @Test
    public void shouldPassValidation() {
        //given
        prepareForTest(TASK_NAME, TASK_DESCRIPTION, ESTIMATE_TIME, ESTIMATE_PRICE);
        //when
        taskValidator.validate(task, errors);
        //then
        assertEquals(0, errors.getErrorCount());
    }

    @Test
    public void shouldFailValidationDueToEmptyTaskName() {
        //given
        prepareForTest(EMPTY, TASK_DESCRIPTION, ESTIMATE_TIME, ESTIMATE_PRICE);
        //when
        taskValidator.validate(task, errors);
        //then
        assertSingleError(REQUIRED_FIELD);
    }

    @Test
    public void shouldFailValidationDueToEmptyTaskDescription() {
        //given
        prepareForTest(TASK_NAME, EMPTY, ESTIMATE_TIME, ESTIMATE_PRICE);
        //when
        taskValidator.validate(task, errors);
        //then
        assertSingleError(REQUIRED_FIELD);
    }

    @Test
    public void shouldFailValidationDueToNegativeEstimateTime() {
        //given
        prepareForTest(TASK_NAME, TASK_DESCRIPTION, NEGATIVE_ESTIMATE_TIME, ESTIMATE_PRICE);
        //when
        taskValidator.validate(task, errors);
        //then
        assertSingleError(NEGATIVE_VALUE);
    }


    @Test
    public void shouldFailValidationDueToNegativeEstimatePrice() {
        //given
        prepareForTest(TASK_NAME, TASK_DESCRIPTION, ESTIMATE_TIME, NEGATIVE_ESTIMATE_PRICE);
        //when
        taskValidator.validate(task, errors);
        //then
        assertSingleError(NEGATIVE_VALUE);
    }

    private void assertSingleError(String errorCode) {
        assertEquals(1, errors.getErrorCount());
        assertEquals(errorCode, errors.getAllErrors().get(0).getCode());
    }

    private void prepareForTest(String taskName, String taskDescription, int estimateTime, int estimatePrice) {
        task = new Task(taskName, taskDescription, estimateTime, estimatePrice);
        errors = new BeanPropertyBindingResult(task, "task");
    }

}