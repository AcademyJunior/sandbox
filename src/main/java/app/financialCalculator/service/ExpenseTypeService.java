package app.financialCalculator.service;

import app.financialCalculator.dao.ExpenseTypeDao;
import app.financialCalculator.model.ExpenseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseTypeService {

    @Autowired
    private ExpenseTypeDao expenseTypeDao;

    public void createExpenseType(ExpenseType expenseType){
        expenseTypeDao.createExpenseType(expenseType);
    }

    public void updateExpenseType(ExpenseType expenseType, long expenseTypeId){
        expenseTypeDao.updateExpenseType(expenseType, expenseTypeId);
    }

    public void deleteExpenseType(long expenseTypeId){
        expenseTypeDao.deleteExpenseType(expenseTypeId);
    }

    public Optional<ExpenseType> getExpenseType(long expenseTypeId){
        return expenseTypeDao.getExpenseType(expenseTypeId);
    }

    public List<ExpenseType> getAllExpenseTypes(){
        return expenseTypeDao.getAllExpenseTypes();
    }
}
