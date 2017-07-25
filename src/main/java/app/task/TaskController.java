package app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new TaskValidator());
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }


    @GetMapping("/tasks/{taskId}")
    public Task getTask(
            @PathVariable int taskId
    ) {
        return taskDao.getTask(taskId);
    }


    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(
            @Valid @RequestBody Task task
    ) {
        taskDao.createTask(task);
        return new ResponseEntity<Task>(task, HttpStatus.OK);

    }


    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(
            @Valid @RequestBody Task task, @PathVariable int taskId
    ) {
        if (taskDao.getTask(taskId) == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        taskDao.updateTask(task, taskId);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }


    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Task> deleteTask(
            @PathVariable int taskId
    ) {
        if (taskDao.getTask(taskId) == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        taskDao.deleteTask(taskId);
        return new ResponseEntity<Task>(HttpStatus.OK);
    }


}
