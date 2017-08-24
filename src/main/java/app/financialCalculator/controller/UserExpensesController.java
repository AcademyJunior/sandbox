package app.financialCalculator.controller;

import app.financialCalculator.service.UserExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("userExpenses")
public class UserExpensesController {

    @Autowired
    private UserExpenseService userExpenseService;

    @GetMapping("/{userId}")
    public double getExpenses(@PathVariable long userId) {
        return userExpenseService.getExpenses(userId);
    }

    @GetMapping("/{userId}/{expenseTypeId}")
    public double getExpenses(@PathVariable long userId, @PathVariable long expenseTypeId) {
        return userExpenseService.getExpenses(userId, expenseTypeId);
    }

    @GetMapping("/{userId}/{minDate}/{maxDate}")
    public double getExpenses(@PathVariable long userId, @PathVariable Date minDate, @PathVariable Date maxDate) {
        return userExpenseService.getExpenses(userId, minDate, maxDate);
    }

    @GetMapping("/{userId}/{minDate}/{maxDate}/{expenseTypeId}")
    public double getExpenses(@PathVariable long userId, @PathVariable Date minDate, @PathVariable Date maxDate, @PathVariable long expenseTypeId) {
        return userExpenseService.getExpenses(userId, minDate, maxDate, expenseTypeId);
    }

    @GetMapping("/getUserExpensesForCurrentDay/{userId}")
    public double getUserExpensesForCurrentDay(@PathVariable long userId) {
        return userExpenseService.getUserExpensesForCurrentDay(userId);
    }

    @GetMapping("/getUserExpensesForCurrentMonth/{userId}")
    public double getUserExpensesForCurrentMonth(@PathVariable long userId) {
        return userExpenseService.getUserExpensesForCurrentMonth(userId);
    }

    @GetMapping("/getUserExpensesForMonth/{userId}/{month}/{year}")
    public double getUserExpensesForMonth(@PathVariable long userId, @PathVariable int month, @PathVariable int year) {
        return userExpenseService.getUserExpensesForMonth(userId, month, year);
    }
}
