package app.financialCalculator.dao;

import app.financialCalculator.model.SavingsGoal;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.SAVINGS_GOAL;

@Repository
public class SavingsGoalDao {

    @Autowired
    private DSLContext dslContext;

    public void createSavingsGoal(SavingsGoal savingsGoal) {
        dslContext.insertInto(SAVINGS_GOAL, SAVINGS_GOAL.SAVINGS_GOAL_NAME, SAVINGS_GOAL.SAVINGS_GOAL_VALUE,
                SAVINGS_GOAL.SAVINGS_GOAL_MONEY_TO_ACCOMPLISH, SAVINGS_GOAL.SAVINGS_GOAL_ACCOMPLISHMENT, SAVINGS_GOAL.SAVINGS_GOAL_USER_ID)
                .values(savingsGoal.getSavingsGoalName(), savingsGoal.getSavingsGoalValue(), savingsGoal.getSavingsGoalValue(),
                        savingsGoal.isSavingsGoalAccomplishment(), savingsGoal.getSavingsGoalUserId())
                .execute();
    }

    public void updateSavingsGoal(SavingsGoal savingsGoal, long savingsGoalId) {
        dslContext.update(SAVINGS_GOAL)
                .set(SAVINGS_GOAL.SAVINGS_GOAL_NAME, savingsGoal.getSavingsGoalName())
                .set(SAVINGS_GOAL.SAVINGS_GOAL_VALUE, savingsGoal.getSavingsGoalValue())
                .set(SAVINGS_GOAL.SAVINGS_GOAL_MONEY_TO_ACCOMPLISH, savingsGoal.getSavingsGoalMoneyToAccomplish())
                .set(SAVINGS_GOAL.SAVINGS_GOAL_ACCOMPLISHMENT, savingsGoal.isSavingsGoalAccomplishment())
                .set(SAVINGS_GOAL.SAVINGS_GOAL_USER_ID, savingsGoal.getSavingsGoalUserId())
                .where(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingsGoalId))
                .execute();
    }

    public void updateSavingsGoal(double savingsGoalMoneyToAccomplish, long savingGoalId) {
        dslContext.update(SAVINGS_GOAL)
                .set(SAVINGS_GOAL.SAVINGS_GOAL_MONEY_TO_ACCOMPLISH, savingsGoalMoneyToAccomplish)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingGoalId))
                .execute();
    }

    public void updateSavingsGoal(boolean savingGoalAccomplishment, long savingGoalId) {
        dslContext.update(SAVINGS_GOAL)
                .set(SAVINGS_GOAL.SAVINGS_GOAL_ACCOMPLISHMENT, savingGoalAccomplishment)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingGoalId))
                .execute();
    }

    public void deleteSavingsGoal(long savingsGoalId) {
        dslContext.delete(SAVINGS_GOAL)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingsGoalId))
                .execute();
    }

    public Optional<SavingsGoal> getSavingsGoal(long savingsGoalId) {
        return dslContext.selectFrom(SAVINGS_GOAL)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingsGoalId))
                .fetchOptionalInto(SavingsGoal.class);
    }

    public Optional<SavingsGoal> getSavingsGoal(long userId, long savingsGoalId) {
        return dslContext.selectFrom(SAVINGS_GOAL)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_USER_ID.eq(userId))
                .and(SAVINGS_GOAL.SAVINGS_GOAL_ID.eq(savingsGoalId))
                .fetchOptionalInto(SavingsGoal.class);
    }

    public List<SavingsGoal> getAllSavingsGoals() {
        return dslContext.selectFrom(SAVINGS_GOAL)
                .fetchInto(SavingsGoal.class);
    }

    public List<SavingsGoal> getAllSavingsGoals(long userId) {
        return dslContext.selectFrom(SAVINGS_GOAL)
                .where(SAVINGS_GOAL.SAVINGS_GOAL_USER_ID.eq(userId))
                .fetchInto(SavingsGoal.class);
    }
}
