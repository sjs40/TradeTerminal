package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

  public static Map<String, Integer> sortByValue(Map<String, Integer> unsorted) {
    List<Map.Entry<String, Integer>> list = new LinkedList<>(unsorted.entrySet());

    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o1.getValue().compareTo(o2.getValue());
      }
    });
    Collections.reverse(list);

    Map<String, Integer> sorted = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
      sorted.put(entry.getKey(), entry.getValue());
    }

    return sorted;
  }

}
