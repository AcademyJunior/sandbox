package app.financialCalculator.service;

import app.financialCalculator.dao.ExpenseDao;
import app.financialCalculator.dao.UserDao;
import app.financialCalculator.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

@Service
public class UsersExpensesStatisticsService {

    private static final int MAX_DATE = 0;
    private static final int MIN_DATE = 1;
    private static final int FIRST_EXPENSE = 0;
    private static final int NO_EXPENSES = 0;
    private static final int NOT_NULL_DIFFERENTIAL = 1;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserExpenseService userExpenseService;

    public Map<Long, Double> usersExpenses(List<Long> listOfUserIds) {
        Map<Long, Double> mapOfUsersExpenses = new TreeMap<>();
        for (Long userId : listOfUserIds) {
            mapOfUsersExpenses.put(userId, userExpenseService.getExpenses(userId));
        }
        return mapOfUsersExpenses;
    }

    public Map<Long, Double> usersExpenses(List<Long> listOfUserIds, long expenseTypeId) {
        Map<Long, Double> mapOfUsersExpenses = new TreeMap<>();
        for (Long userId : listOfUserIds) {
            mapOfUsersExpenses.put(userId, userExpenseService.getExpenses(userId, expenseTypeId));
        }
        return mapOfUsersExpenses;
    }

    public Map<Long, Double> usersExpenses(List<Long> listOfUserIds, Date minDate, Date maxDate) {
        Map<Long, Double> mapOfUsersExpenses = new TreeMap<>();
        for (Long userId : listOfUserIds) {
            mapOfUsersExpenses.put(userId, userExpenseService.getExpenses(userId, minDate, maxDate));
        }
        return mapOfUsersExpenses;
    }

    public Map<Long, Double> usersAverageDailyExpenses(List<Long> listOfUserIds) {
        Map<Long, Double> mapOfUsersExpenses = new TreeMap<>();
        for (Long userId : listOfUserIds) {
            long daysBetween = daysBetween(userId);
            if (daysBetween == 0)
                mapOfUsersExpenses.put(userId, 0.00);
            else
                mapOfUsersExpenses.put(userId, userExpenseService.getExpenses(userId) / daysBetween);
        }
        return mapOfUsersExpenses;
    }

    public Map<Long, Double> usersAverageMonthlyExpenses(List<Long> listOfUserIds) {
        Map<Long, Double> mapOfUsersExpenses = new TreeMap<>();
        for (Long userId : listOfUserIds) {
            long months = monthsBetween(userId);
            if (months == 0)
                mapOfUsersExpenses.put(userId, 0.00);
            else
                mapOfUsersExpenses.put(userId, userExpenseService.getExpenses(userId) / months);
        }
        return mapOfUsersExpenses;
    }

    private long daysBetween(long userId) {
        List<Date> listOfDates = getExtremeDateBounds(userId);
        if (listOfDates == null)
            return NO_EXPENSES;
        if (DAYS.between(listOfDates.get(MIN_DATE).toLocalDate(), listOfDates.get(MAX_DATE).toLocalDate()) == 0) {
            return NOT_NULL_DIFFERENTIAL;
        }
        return DAYS.between(listOfDates.get(MIN_DATE).toLocalDate(), listOfDates.get(MAX_DATE).toLocalDate());
    }

    private long monthsBetween(long userId) {
        List<Date> listOfDates = getExtremeDateBounds(userId);
        if (listOfDates == null)
            return NO_EXPENSES;
        if (listOfDates.get(MAX_DATE).toLocalDate().getMonthValue() - listOfDates.get(MIN_DATE).toLocalDate().getMonthValue() == 0)
            return NOT_NULL_DIFFERENTIAL;
        return MONTHS.between(listOfDates.get(MIN_DATE).toLocalDate().withDayOfMonth(1), listOfDates.get(MAX_DATE).toLocalDate().withDayOfMonth(1));
    }

    private List<Date> getExtremeDateBounds(long userId) {
        List<Expense> userExpenses = expenseDao.getExpenses(userId);
        if (userExpenses.isEmpty())
            return null;
        Date minDate = expenseDao.getExpenses(userId).get(FIRST_EXPENSE).getExpenseDate();
        Date maxDate = minDate;
        for (Expense e : userExpenses) {
            if (e.getExpenseDate().after(maxDate)) {
                maxDate = e.getExpenseDate();
            }
            if (e.getExpenseDate().before(minDate)) {
                minDate = e.getExpenseDate();
            }
        }
        List<Date> listOfDates = new ArrayList<>();
        listOfDates.add(maxDate);
        listOfDates.add(minDate);
        return listOfDates;
    }
}
