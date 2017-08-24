package app.financialCalculator.service;

import app.financialCalculator.dao.SavingsGoalDao;
import app.financialCalculator.model.SavingsGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsGoalService {
    @Autowired
    private SavingsGoalDao savingsGoalDao;

    public void createSavingsGoal(SavingsGoal savingsGoal) {
        savingsGoalDao.createSavingsGoal(savingsGoal);
    }

    public void updateSavingsGoal(SavingsGoal savingsGoal, long savingsGoalId) {
        savingsGoalDao.updateSavingsGoal(savingsGoal, savingsGoalId);
    }

    public void deleteSavingsGoal(long savingsGoalId) {
        savingsGoalDao.deleteSavingsGoal(savingsGoalId);
    }

    public Optional<SavingsGoal> getSavingsGoal(long savingsGoalId) {
        return savingsGoalDao.getSavingsGoal(savingsGoalId);
    }

    public List<SavingsGoal> getAllSavingsGoals() {
        return savingsGoalDao.getAllSavingsGoals();
    }

    public List<SavingsGoal> getAllSavingsGoals(long userId) {
        return savingsGoalDao.getAllSavingsGoals(userId);
    }

}
