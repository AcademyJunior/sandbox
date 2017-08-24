package app.financialCalculator.service;

import app.financialCalculator.dao.ExpenseDao;
import app.financialCalculator.dao.ExpenseTypeDao;
import app.financialCalculator.dao.UserDao;
import app.financialCalculator.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ExpenseTypeDao expenseTypeDao;

    public double getExpenses(long userId) {
        return expenseDao.getExpenses(userId, getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getExpenses(long userId, long expenseTypeId) {
        return expenseDao.getExpenses(userId, expenseTypeId, getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getExpenses(long userId, Date minDate, Date maxDate) {
        return expenseDao.getExpenses(userId, minDate, maxDate, getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getExpenses(long userId, Date minDate, Date maxDate, long expenseTypeId) {
        return expenseDao.getExpenses(userId, minDate, maxDate, expenseTypeId, getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getUserExpensesForCurrentDay(long userId) {
        return expenseDao.getUserExpensesForCurrentDay(userId, LocalDate.now(), getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getUserExpensesForCurrentMonth(long userId) {
        return expenseDao.getUserExpensesForMonth(userId, LocalDate.now().withDayOfMonth(1), LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1), getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    public double getUserExpensesForMonth(long userId, int month, int year) {
        return expenseDao.getUserExpensesForMonth(userId, LocalDate.of(year, month, 1), LocalDate.of(year, month + 1, 1).minusDays(1), getSavingsExpenseTypeId())
                .stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
    }

    private long getSavingsExpenseTypeId() {
        return expenseTypeDao.getExpenseType("Savings");
    }
}
