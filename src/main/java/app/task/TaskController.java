package app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<Task> getTask(@PathVariable long taskId) {
        if (taskDao.getTask(taskId).isPresent()) {
            return new ResponseEntity<Task>(taskDao.getTask(taskId).get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        taskDao.createTask(task);
        return new ResponseEntity<Task>(task, HttpStatus.OK);

    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @PathVariable long taskId) {
        if (taskDao.getTask(taskId).isPresent()) {
            taskDao.updateTask(task, taskId);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable long taskId) {
        if (taskDao.getTask(taskId).isPresent()) {
            taskDao.deleteTask(taskId);
            return new ResponseEntity<Task>(HttpStatus.OK);
        }
        return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
    }

}
