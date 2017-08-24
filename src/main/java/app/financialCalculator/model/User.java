package app.financialCalculator.model;


public class User {

    private String userName;
    private String userJob;
    private double userEarnings;
    private double userSavings;


    public String getUserName() {
        return userName;
    }

    public String getUserJob() {
        return userJob;
    }

    public double getUserEarnings() {
        return userEarnings;
    }

    public double getUserSavings() {
        return userSavings;
    }

    public void setUserSavings(double userSavings) {
        this.userSavings = userSavings;
    }
}

