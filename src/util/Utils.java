package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

import model.Stocks;
import model.StocksFactory;
import model.StocksType;
import model.chartdata.ChartData;
import view.charts.LineChart;

public class Utils {

  public static String currencyFormat(BigInteger i) {
    return NumberFormat.getCurrencyInstance().format(i);
  }

  public static String currencyFormat(BigDecimal d) {
    return NumberFormat.getCurrencyInstance().format(d);
  }

  public static String getResult(String ticker, String type) throws IllegalArgumentException {
    Stocks stock;
    type = type.toLowerCase();
    if (type.split(" ").length > 1 && type.split(" ")[0].equals("chart")) {
      String[] typeArr = type.split(" ");
      String chart = typeArr[0];
      String period = typeArr[1];
      LineChart lineChart = StocksFactory.buildChart(ticker, period);
      lineChart.pack();
      lineChart.setVisible(true);
      return "";
    }
    switch (type) {
      case "q":
        stock = StocksFactory.buildStocks(ticker, StocksType.QUOTE);
        break;
      case "comp":
        stock = StocksFactory.buildStocks(ticker, StocksType.COMPANY);
        break;
      case "news":
        stock = StocksFactory.buildStocks(ticker, StocksType.NEWS);
        break;
      case "fin":
        stock = StocksFactory.buildStocks(ticker, StocksType.FINANCIAL);
        break;
      case "ear":
        stock = StocksFactory.buildStocks(ticker, StocksType.EARNING);
        break;
      default:
        throw new IllegalArgumentException("Invalid Command");
    }

    stock.loadStocks(ticker);
    return stock.toString();
  }

}
