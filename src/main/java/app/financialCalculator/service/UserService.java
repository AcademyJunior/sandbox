package app.financialCalculator.service;

import app.financialCalculator.dao.UserDao;
import app.financialCalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateUser(User user, long userId){
        userDao.updateUser(user,userId);
    }

    public void deleteUser(long userId){
        userDao.deleteUser(userId);
    }

    public Optional<User> getUser(long userId){
        return userDao.getUser(userId);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
