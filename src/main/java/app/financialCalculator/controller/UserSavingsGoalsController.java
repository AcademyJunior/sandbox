package app.financialCalculator.controller;

import app.financialCalculator.service.UserSavingsGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.sql.Date;

@RestController
@RequestMapping("userSavingsGoals")
public class UserSavingsGoalsController {

    @Autowired
    private UserSavingsGoalsService userSavingsGoalsService;

    @PutMapping("/updateMonthlySavings/{userId}/{month}/{year}")
    public void updateMonthlySavings(@PathVariable long userId, @PathVariable int month, @PathVariable int year) {
        userSavingsGoalsService.updateMonthlySavings(userId, month, year);
    }

    @PutMapping("/updateSavingGoal/{userId}/{savingsGoalId}/{savingGoalMoney}")
    public void updateSavingGoal(@PathVariable long userId, @PathVariable long savingsGoalId, @PathVariable double savingGoalMoney) {
        userSavingsGoalsService.updateSavingGoal(userId, savingsGoalId, savingGoalMoney);
    }

    @GetMapping("/getCurrentMonthSavings/{userId}")
    public ResponseEntity<Double> getCurrentMonthSavings(@PathVariable long userId) {
        if (userSavingsGoalsService.getCurrentMonthSavings(userId) == null) {
            return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Double>(userSavingsGoalsService.getCurrentMonthSavings(userId), HttpStatus.OK);
    }

    @GetMapping("/savingsGoalDateByDailySavings/{userId}/{savingsGoalId}/{dailySavings}")
    public ResponseEntity<Date> savingsGoalDateByDailySavings(@PathVariable long userId, @PathVariable long savingsGoalId, @PathVariable double dailySavings) {
        if (userSavingsGoalsService.savingsGoalDateByDailySavings(userId, savingsGoalId, dailySavings) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSavingsGoalsService.savingsGoalDateByDailySavings(userId, savingsGoalId, dailySavings), HttpStatus.OK);
    }

    @GetMapping("/savingsGoalDateByMonthlySavings/{userId}/{savingsGoalId}/{monthlySavings}")
    public ResponseEntity<Date> savingsGoalDateByMonthlySavings(@PathVariable long userId, @PathVariable long savingsGoalId, @PathVariable double monthlySavings) {
        if (userSavingsGoalsService.savingsGoalDateByMonthlySavings(userId, savingsGoalId, monthlySavings) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSavingsGoalsService.savingsGoalDateByMonthlySavings(userId, savingsGoalId, monthlySavings), HttpStatus.OK);
    }

    @GetMapping("/savingsGoalDailySavingTillDate/{userId}/{savingsGoalId}/{dateOfAccomplishment}")
    public ResponseEntity<Double> savingsGoalDailySavingTillDate(@PathVariable long userId, @PathVariable long savingsGoalId, @PathVariable Date dateOfAccomplishment) {
        if (userSavingsGoalsService.savingsGoalDailySavingTillDate(userId, savingsGoalId, dateOfAccomplishment) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSavingsGoalsService.savingsGoalDailySavingTillDate(userId, savingsGoalId, dateOfAccomplishment), HttpStatus.OK);
    }
}
