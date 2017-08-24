package app.financialCalculator.controller;

import app.financialCalculator.model.User;
import app.financialCalculator.service.ExpenseService;
import app.financialCalculator.service.SavingsGoalService;
import app.financialCalculator.service.UserService;
import app.financialCalculator.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SavingsGoalService savingsGoalService;

    @Autowired
    private ExpenseService expenseService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @PostMapping
    public void addUser(@Valid @RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable long userId) {
        if (userService.getUser(userId).isPresent()) {
            userService.updateUser(user, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable long userId) {
        if (userService.getUser(userId).isPresent()) {
            if (expenseService.getExpenses(userId).isEmpty() && savingsGoalService.getAllSavingsGoals(userId).isEmpty()) {
                userService.deleteUser(userId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        if (userService.getUser(userId).isPresent()) {
            return new ResponseEntity<>(userService.getUser(userId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
