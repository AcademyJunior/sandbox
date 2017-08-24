package app.financialCalculator.service;

import app.financialCalculator.dao.ExpenseDao;
import app.financialCalculator.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    public void createExpense(Expense expense) {
        expenseDao.createExpense(expense);
    }

    public void updateExpense(Expense expense, long expenseId) {
        expenseDao.updateExpense(expense, expenseId);
    }

    public void deleteExpense(long expenseId) {
        expenseDao.deleteExpense(expenseId);
    }

    public Optional<Expense> getExpense(long expenseId) {
        return expenseDao.getExpense(expenseId);
    }

    public List<Expense> getExpenses() {
        return expenseDao.getExpenses();
    }

    public List<Expense> getExpensesByType(long expenseTypeId) {
        return expenseDao.getExpensesByType(expenseTypeId);
    }

    public List<Expense> getExpenses(long userId) {
        return expenseDao.getExpenses(userId);
    }
}
