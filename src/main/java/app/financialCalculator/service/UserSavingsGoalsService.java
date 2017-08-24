package app.financialCalculator.service;

import app.financialCalculator.dao.ExpenseDao;
import app.financialCalculator.dao.ExpenseTypeDao;
import app.financialCalculator.dao.SavingsGoalDao;
import app.financialCalculator.dao.UserDao;
import app.financialCalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class UserSavingsGoalsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SavingsGoalDao savingsGoalDao;

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private ExpenseTypeDao expenseTypeDao;

    @Autowired
    private UserExpenseService userExpenseService;

    public void updateMonthlySavings(long userId, int month, int year) {
        if (userDao.getUser(userId).isPresent()) {
            double monthlySavings = userDao.getUser(userId).get().getUserEarnings() - userExpenseService.getUserExpensesForMonth(userId, month, year);
            User user = userDao.getUser(userId).get();
            if (month < LocalDate.now().getMonthValue()) {
                user.setUserSavings(monthlySavings + user.getUserSavings());
                userDao.updateUser(user, userId);
                if (monthlySavings > 0) {
                    expenseDao.createExpense("User monthly savings", "Savings in " + Month.of(month),
                            monthlySavings, Date.valueOf(LocalDate.of(year, month, 1)), userId, expenseTypeDao.getExpenseType("Savings"));
                }
            }
        }
    }

    public void updateSavingGoal(long userId, long savingsGoalId, double userSavingsAllocation) {
        double userSavings = userDao.getUser(userId).get().getUserSavings();
        boolean isSavingsGoalAccomplished = savingsGoalDao.getSavingsGoal(savingsGoalId).get().isSavingsGoalAccomplishment();
        double savingsGoalMoneyToAccomplish = savingsGoalDao.getSavingsGoal(savingsGoalId).get().getSavingsGoalMoneyToAccomplish();
        if (!isSavingsGoalAccomplished && userSavings > 0 && userSavings >= userSavingsAllocation && savingsGoalDao.getSavingsGoal(savingsGoalId).get().getSavingsGoalUserId() == userId) {
            if (savingsGoalMoneyToAccomplish <= userSavingsAllocation) {
                savingsGoalDao.updateSavingsGoal(0, savingsGoalId);
                savingsGoalDao.updateSavingsGoal(true, savingsGoalId);
                userDao.updateUser(userSavings - savingsGoalMoneyToAccomplish, userId);
            } else {
                savingsGoalDao.updateSavingsGoal(savingsGoalMoneyToAccomplish - userSavingsAllocation, savingsGoalId);
                userDao.updateUser(userSavings - userSavingsAllocation, userId);
            }
        }
    }

    public Double getCurrentMonthSavings(long userId) {
        if (userDao.getUser(userId).isPresent()) {
            return userDao.getUser(userId).get().getUserEarnings() - userExpenseService.getUserExpensesForCurrentMonth(userId);
        }
        return null;
    }

    public Date savingsGoalDateByDailySavings(long userId, long savingsGoalId, double dailySavings) {
        if (savingsGoalDao.getSavingsGoal(userId, savingsGoalId).isPresent()) {
            double savingsGoalMoneyToAccomplish = savingsGoalDao.getSavingsGoal(userId, savingsGoalId).get().getSavingsGoalMoneyToAccomplish();
            int howManyDaysToAccomplish = (int) (savingsGoalMoneyToAccomplish / dailySavings) + 1;
            return Date.valueOf(LocalDate.now().plusDays(howManyDaysToAccomplish));
        }
        return null;
    }

    public Date savingsGoalDateByMonthlySavings(long userId, long savingsGoalId, double monthlySavings) {
        return savingsGoalDateByDailySavings(userId, savingsGoalId, monthlySavings / LocalDate.now().lengthOfMonth());
    }

    public Double savingsGoalDailySavingTillDate(long userId, long savingsGoalId, Date dateOfAccomplishment) {
        if (savingsGoalDao.getSavingsGoal(userId, savingsGoalId).isPresent()) {
            long daysBetween = DAYS.between(LocalDate.now(), dateOfAccomplishment.toLocalDate());
            double savingsGoalMoneyToAccomplish = savingsGoalDao.getSavingsGoal(userId, savingsGoalId).get().getSavingsGoalMoneyToAccomplish();
            return (Math.round(savingsGoalMoneyToAccomplish / daysBetween * 100)) / 100.00;
        }
        return null;
    }
}
