package app.financialCalculator.controller;

import app.financialCalculator.model.ExpenseType;
import app.financialCalculator.service.ExpenseService;
import app.financialCalculator.service.ExpenseTypeService;
import app.financialCalculator.validator.ExpenseTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("expenseType")
public class ExpenseTypeController {

    @Autowired
    private ExpenseTypeService expenseTypeService;

    @Autowired
    private ExpenseService expenseService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new ExpenseTypeValidator());
    }

    @PostMapping
    public void addExpenseType(@Valid @RequestBody ExpenseType expenseType) {
        expenseTypeService.createExpenseType(expenseType);
    }

    @PutMapping("/{expenseTypeId}")
    public ResponseEntity<ExpenseType> updateExpenseType(@Valid @RequestBody ExpenseType expenseType, @PathVariable long expenseTypeId) {
        if (expenseTypeService.getExpenseType(expenseTypeId).isPresent()) {
            expenseTypeService.updateExpenseType(expenseType, expenseTypeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{expenseTypeId}")
    public ResponseEntity<ExpenseType> deleteExpenseType(@PathVariable long expenseTypeId) {
        if (expenseTypeService.getExpenseType(expenseTypeId).isPresent()) {
            if (expenseService.getExpensesByType(expenseTypeId).isEmpty()) {
                expenseTypeService.deleteExpenseType(expenseTypeId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeService.getAllExpenseTypes();
    }

    @GetMapping("/{expenseTypeId}")
    public ResponseEntity<ExpenseType> getExpenseType(@PathVariable long expenseTypeId) {
        if (expenseTypeService.getExpenseType(expenseTypeId).isPresent()) {
            return new ResponseEntity<>(expenseTypeService.getExpenseType(expenseTypeId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
