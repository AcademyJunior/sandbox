package app.financialCalculator.controller;


import app.financialCalculator.model.Expense;
import app.financialCalculator.service.ExpenseService;
import app.financialCalculator.service.ExpenseTypeService;
import app.financialCalculator.service.UserService;
import app.financialCalculator.validator.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseTypeService expenseTypeService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new ExpenseValidator());
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {
        if (userService.getUser(expense.getExpenseUserId()).isPresent() && expenseTypeService.getExpenseType(expense.getExpenseTypeId()).isPresent()) {
            expenseService.createExpense(expense);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@Valid @RequestBody Expense expense, @PathVariable long expenseId) {
        if (expenseService.getExpense(expenseId).isPresent()) {
            expenseService.updateExpense(expense, expenseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable long expenseId) {
        if (expenseService.getExpense(expenseId).isPresent()) {
            expenseService.deleteExpense(expenseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getExpenses();
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpense(@PathVariable long expenseId) {
        if (expenseService.getExpense(expenseId).isPresent()) {
            return new ResponseEntity<>(expenseService.getExpense(expenseId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
