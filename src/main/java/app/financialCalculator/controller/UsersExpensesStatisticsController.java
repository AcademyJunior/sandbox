package app.financialCalculator.controller;

import app.financialCalculator.service.UsersExpensesStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("usersExpensesStatistics")
public class UsersExpensesStatisticsController {

    @Autowired
    private UsersExpensesStatisticsService usersExpensesStatisticsService;

    @PostMapping("/usersExpenses")
    public Map<Long, Double> usersExpenses(@RequestBody List<Long> listOfUsersId) {
        return usersExpensesStatisticsService.usersExpenses(listOfUsersId);
    }

    @PostMapping("usersExpenses/{expenseTypeId}")
    public Map<Long, Double> usersExpenses(@RequestBody List<Long> listOfUsersId, @PathVariable long expenseTypeId) {
        return usersExpensesStatisticsService.usersExpenses(listOfUsersId, expenseTypeId);
    }

    @PostMapping("usersExpenses/{minDate}/{maxDate}")
    public Map<Long, Double> usersExpenses(@RequestBody List<Long> listOfUsersId, @PathVariable Date minDate, @PathVariable Date maxDate) {
        return usersExpensesStatisticsService.usersExpenses(listOfUsersId, minDate, maxDate);
    }

    @PostMapping("/usersAverageDailyExpenses")
    public Map<Long, Double> usersAverageDailyExpenses(@RequestBody List<Long> listOfUsersId) {
        return usersExpensesStatisticsService.usersAverageDailyExpenses(listOfUsersId);
    }

    @PostMapping("/usersAverageMonthlyExpenses")
    public Map<Long, Double> usersAverageMonthlyExpenses(@RequestBody List<Long> listOfUsersId) {
        return usersExpensesStatisticsService.usersAverageMonthlyExpenses(listOfUsersId);
    }
}
