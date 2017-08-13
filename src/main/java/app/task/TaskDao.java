package app.task;


import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.TASKS;

@Repository
public class TaskDao {

    @Autowired
    private DSLContext dslContext;


    public void createTask(Task task){
        dslContext.insertInto(TASKS,TASKS.TASK_NAME,TASKS.TASK_DESCRIPTION, TASKS.ESTIMATE_TIME,TASKS.ESTIMATE_PRICE)
                .values(task.getTaskName(),task.getTaskDescription(),task.getEstimateTime(),task.getEstimatePrice())
                .execute();
    }

    public void updateTask(Task task, long taskId){
        dslContext.update(TASKS)
                .set(TASKS.TASK_NAME,task.getTaskName())
                .set(TASKS.TASK_DESCRIPTION,task.getTaskDescription())
                .set(TASKS.ESTIMATE_TIME,task.getEstimateTime())
                .set(TASKS.ESTIMATE_PRICE,task.getEstimatePrice())
                .where(TASKS.TASK_ID.eq(taskId))
                .execute();
    }


    public void deleteTask(long taskId){
        dslContext.delete(TASKS)
                .where(TASKS.TASK_ID.eq(taskId))
                .execute();
    }


    public Optional<Task> getTask(long taskId){
        return dslContext.selectFrom(TASKS)
                .where(TASKS.TASK_ID.eq(taskId))
                .fetchOptionalInto(Task.class);
    }


    public List<Task> getAllTasks(){
        return dslContext.selectFrom(TASKS)
                .fetchInto(Task.class);
    }
}
