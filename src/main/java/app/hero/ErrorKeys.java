package app.hero;

public interface ErrorKeys {
    String EMPTY_NAME = "Name can not be empty.";
    String STATS_TOO_BIG = "Sum of stats can not be bigger than 10.";
    String STAT_TOO_BIG = "A statistic can not be bigger than 5.";
    String STAT_TOO_SMALL = "A statistic can not be a negative value.";
    int MAX_STAT = 5;
    int MIN_STAT = 0;
    int MAX_STATS_SUM = 10;
}
