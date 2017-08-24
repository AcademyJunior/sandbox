package app.financialCalculator.dao;

import app.financialCalculator.model.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.USERS;

@Repository
public class UserDao {

    @Autowired
    private DSLContext dslContext;

    public void createUser(User user) {
        dslContext.insertInto(USERS, USERS.USER_NAME, USERS.USER_JOB, USERS.USER_EARNINGS, USERS.USER_SAVINGS)
                .values(user.getUserName(), user.getUserJob(), user.getUserEarnings(), user.getUserSavings())
                .execute();
    }

    public void updateUser(User user, long userId) {
        dslContext.update(USERS)
                .set(USERS.USER_NAME, user.getUserName())
                .set(USERS.USER_JOB, user.getUserJob())
                .set(USERS.USER_EARNINGS, user.getUserEarnings())
                .set(USERS.USER_SAVINGS, user.getUserSavings())
                .where(USERS.USER_ID.eq(userId))
                .execute();
    }

    public void updateUser(double userSavings, long userId) {
        dslContext.update(USERS)
                .set(USERS.USER_SAVINGS, userSavings)
                .where(USERS.USER_ID.eq(userId))
                .execute();
    }

    public void deleteUser(long userId) {
        dslContext.delete(USERS)
                .where(USERS.USER_ID.eq(userId))
                .execute();
    }

    public Optional<User> getUser(long userId) {
        return dslContext.selectFrom(USERS)
                .where(USERS.USER_ID.eq(userId))
                .fetchOptionalInto(User.class);
    }

    public List<User> getAllUsers() {
        return dslContext.selectFrom(USERS)
                .fetchInto(User.class);
    }
}
