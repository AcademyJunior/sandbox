package app.financialCalculator.dao;

import app.financialCalculator.model.Expense;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.EXPENSES;

@Repository
public class ExpenseDao {
    @Autowired
    private DSLContext dslContext;

    public void createExpense(Expense expense) {
        dslContext.insertInto(EXPENSES, EXPENSES.EXPENSE_NAME, EXPENSES.EXPENSE_DESCRIPTION,
                EXPENSES.EXPENSE_VALUE, EXPENSES.EXPENSE_DATE,
                EXPENSES.EXPENSE_USER_ID, EXPENSES.EXPENSE_TYPE_ID)
                .values(expense.getExpenseName(), expense.getExpenseDescription(), expense.getExpenseValue(),
                        expense.getExpenseDate(), expense.getExpenseUserId(), expense.getExpenseTypeId())
                .execute();
    }

    public void createExpense(String expenseName, String expenseDescription, double expenseValue, Date expenseDate,
                              long expenseUserId, long expenseTypeId) {
        dslContext.insertInto(EXPENSES, EXPENSES.EXPENSE_NAME, EXPENSES.EXPENSE_DESCRIPTION,
                EXPENSES.EXPENSE_VALUE, EXPENSES.EXPENSE_DATE, EXPENSES.EXPENSE_USER_ID, EXPENSES.EXPENSE_TYPE_ID)
                .values(expenseName, expenseDescription, expenseValue, expenseDate, expenseUserId, expenseTypeId)
                .execute();
    }

    public void updateExpense(Expense expense, long expenseId) {
        dslContext.update(EXPENSES)
                .set(EXPENSES.EXPENSE_NAME, expense.getExpenseName())
                .set(EXPENSES.EXPENSE_DESCRIPTION, expense.getExpenseDescription())
                .set(EXPENSES.EXPENSE_VALUE, expense.getExpenseValue())
                .set(EXPENSES.EXPENSE_DATE, expense.getExpenseDate())
                .set(EXPENSES.EXPENSE_USER_ID, expense.getExpenseUserId())
                .set(EXPENSES.EXPENSE_TYPE_ID, expense.getExpenseTypeId())
                .where(EXPENSES.EXPENSE_ID.eq(expenseId))
                .execute();
    }

    public void deleteExpense(long expenseId) {
        dslContext.delete(EXPENSES)
                .where(EXPENSES.EXPENSE_ID.eq(expenseId))
                .execute();
    }

    public Optional<Expense> getExpense(long expenseId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_ID.eq(expenseId))
                .fetchOptionalInto(Expense.class);
    }

    public List<Expense> getExpenses() {
        return dslContext.selectFrom(EXPENSES)
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpenses(long userId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpensesByType(long expenseTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_TYPE_ID.eq(expenseTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpenses(long userId, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpenses(long userId, long expenseTypeId, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_TYPE_ID.eq(expenseTypeId))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpenses(long userId, Date minDate, Date maxDate, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_DATE.between(minDate, maxDate))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getExpenses(long userId, Date minDate, Date maxDate, long expenseTypeId, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_DATE.between(minDate, maxDate))
                .and(EXPENSES.EXPENSE_USER_ID.eq(expenseTypeId))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getUserExpensesForCurrentDay(long userId, LocalDate currentDay, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_DATE.cast(LocalDate.class).eq(currentDay))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

    public List<Expense> getUserExpensesForMonth(long userId, LocalDate firstDay, LocalDate lastDay, long savingsTypeId) {
        return dslContext.selectFrom(EXPENSES)
                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
                .and(EXPENSES.EXPENSE_DATE.cast(LocalDate.class).between(firstDay, lastDay))
                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
                .fetchInto(Expense.class);
    }

//    public List<Expense> getUserExpensesForMonth(long userId, LocalDate firstDay, LocalDate lastDay, long savingsTypeId) {
//        return dslContext.selectFrom(EXPENSES)
//                .where(EXPENSES.EXPENSE_USER_ID.eq(userId))
//                .and(EXPENSES.EXPENSE_DATE.cast(LocalDate.class).between(firstDay, lastDay))
//                .and(EXPENSES.EXPENSE_TYPE_ID.notEqual(savingsTypeId))
//                .fetchInto(Expense.class);
//
//    }
}
