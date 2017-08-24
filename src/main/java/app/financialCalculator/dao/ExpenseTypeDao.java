package app.financialCalculator.dao;


import app.financialCalculator.model.ExpenseType;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.EXPENSE_TYPES;

@Repository
public class ExpenseTypeDao {
    @Autowired
    private DSLContext dslContext;

    public void createExpenseType(ExpenseType expenseType) {
        dslContext.insertInto(EXPENSE_TYPES, EXPENSE_TYPES.EXPENSE_TYPE_NAME)
                .values(expenseType.getExpenseTypeName())
                .execute();
    }

    public void updateExpenseType(ExpenseType expenseType, long expenseTypeId) {
        dslContext.update(EXPENSE_TYPES)
                .set(EXPENSE_TYPES.EXPENSE_TYPE_NAME, expenseType.getExpenseTypeName())
                .where(EXPENSE_TYPES.EXPENSE_TYPE_ID.eq(expenseTypeId))
                .execute();
    }

    public void deleteExpenseType(long expenseTypeId) {
        dslContext.delete(EXPENSE_TYPES)
                .where(EXPENSE_TYPES.EXPENSE_TYPE_ID.eq(expenseTypeId))
                .execute();
    }

    public Optional<ExpenseType> getExpenseType(long expenseTypeId) {
        return dslContext.selectFrom(EXPENSE_TYPES)
                .where(EXPENSE_TYPES.EXPENSE_TYPE_ID.eq(expenseTypeId))
                .fetchOptionalInto(ExpenseType.class);
    }

    public long getExpenseType(String name) {
        return dslContext.select(EXPENSE_TYPES.EXPENSE_TYPE_ID)
                .from(EXPENSE_TYPES)
                .where(EXPENSE_TYPES.EXPENSE_TYPE_NAME.eq(name))
                .fetchAny()
                .into(ExpenseType.class)
                .getExpenseTypeId();
    }

    public List<ExpenseType> getAllExpenseTypes() {
        return dslContext.selectFrom(EXPENSE_TYPES)
                .fetchInto(ExpenseType.class);
    }
}
