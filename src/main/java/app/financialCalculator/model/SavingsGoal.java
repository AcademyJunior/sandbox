package app.financialCalculator.model;

public class SavingsGoal {
    private String savingsGoalName;
    private double savingsGoalValue;
    private double savingsGoalMoneyToAccomplish;
    private boolean savingsGoalAccomplishment;
    private long savingsGoalUserId;

    public String getSavingsGoalName() {
        return savingsGoalName;
    }

    public double getSavingsGoalValue() {
        return savingsGoalValue;
    }

    public double getSavingsGoalMoneyToAccomplish() {
        return savingsGoalMoneyToAccomplish;
    }

    public boolean isSavingsGoalAccomplishment() {
        return savingsGoalAccomplishment;
    }

    public long getSavingsGoalUserId() {
        return savingsGoalUserId;
    }

}
