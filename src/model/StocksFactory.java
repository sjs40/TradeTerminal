package model;

import model.chartdata.ChartData;
import model.earnings.Earning;
import model.financials.Financial;
import model.news.News;
import model.stockdata.DataFunction;
import view.charts.LineChart;

public class StocksFactory {

  public static Stocks buildStocks(String ticker, StocksType type) {
    switch (type) {
      case QUOTE:
        return new Quote(ticker);
      case COMPANY:
        return new Company(ticker);
      case NEWS:
        return new News(ticker);
      case EARNING:
        return new Earning(ticker);
      case FINANCIAL:
        return new Financial(ticker);
    }
    return null;
  }

  // Intraday, daily, weekly, monthly
  public static LineChart buildChart(String ticker, String period) {
    switch (period) {
      case "intra":
        return new LineChart(DataFunction.DAILY_ADJUST, ticker);
      case "day":
        return new LineChart(DataFunction.DAILY_ADJUST, ticker);
      case "week":
        return new LineChart(DataFunction.WEEKLY_ADJUST, ticker);
      case "month":
        return new LineChart(DataFunction.MONTHLY, ticker);
    }
    return null;
  }

}
