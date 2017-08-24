package app.financialCalculator.controller;

import app.financialCalculator.model.SavingsGoal;
import app.financialCalculator.service.SavingsGoalService;
import app.financialCalculator.service.UserService;
import app.financialCalculator.validator.SavingsGoalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("savingsGoal")
public class SavingsGoalController {

    @Autowired
    private SavingsGoalService savingsGoalService;

    @Autowired
    private UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new SavingsGoalValidator());
    }

    @PostMapping
    public ResponseEntity<SavingsGoal> addSavingsGoal(@Valid @RequestBody SavingsGoal savingsGoal) {
        if (userService.getUser(savingsGoal.getSavingsGoalUserId()).isPresent()) {
            savingsGoalService.createSavingsGoal(savingsGoal);
            return new ResponseEntity<>(savingsGoal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{savingsGoalId}")
    public ResponseEntity<SavingsGoal> updateSavingsGoal(@Valid @RequestBody SavingsGoal savingsGoal, @PathVariable long savingsGoalId) {
        if (savingsGoalService.getSavingsGoal(savingsGoalId).isPresent()) {
            savingsGoalService.updateSavingsGoal(savingsGoal, savingsGoalId);
            return new ResponseEntity<>(savingsGoalService.getSavingsGoal(savingsGoalId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{savingsGoalId}")
    public ResponseEntity<SavingsGoal> deleteSavingsGoal(@PathVariable long savingsGoalId) {
        if (savingsGoalService.getSavingsGoal(savingsGoalId).isPresent()) {
            savingsGoalService.deleteSavingsGoal(savingsGoalId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<SavingsGoal> getAllSavingsGoals() {
        return savingsGoalService.getAllSavingsGoals();
    }

    @GetMapping("/{savingsGoalId}")
    public ResponseEntity<SavingsGoal> getSavingsGoal(@PathVariable long savingsGoalId) {
        if (savingsGoalService.getSavingsGoal(savingsGoalId).isPresent()) {
            return new ResponseEntity<>(savingsGoalService.getSavingsGoal(savingsGoalId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
