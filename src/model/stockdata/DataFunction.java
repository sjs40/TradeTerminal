package model.stockdata;

public enum DataFunction {
  INTRADAY("TIME_SERIES_INTRADAY", "5min"),
  DAILY("TIME_SERIES_DAILY", "daily"),
  DAILY_ADJUST("TIME_SERIES_DAILY_ADJUSTED", "daily"),
  WEEKLY("TIME_SERIES_WEEKLY", "weekly"),
  WEEKLY_ADJUST("TIME_SERIES_WEEKLY_ADJUSTED", "weekly"),
  MONTHLY("TIME_SERIES_MONTHLY", "monthy");

  String function;
  String period;

  DataFunction(String function, String period) {
    this.function = function;
    this.period = period;
  }

  public String getFunction() {
    return function;
  }

  public String getPeriod() {
    return period;
  }
}
