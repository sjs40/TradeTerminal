package util;

import model.Stocks;
import model.StocksFactory;
import model.StocksType;

public class Utils {

  public static String getResult(String ticker, String type) {
    Stocks stock;
    type = type.toLowerCase();
    switch (type) {
      case "q":
        stock = StocksFactory.buildStocks(ticker, StocksType.QUOTE);
        break;
      case "c":
        stock = StocksFactory.buildStocks(ticker, StocksType.COMPANY);
        break;
      case "n":
        stock = StocksFactory.buildStocks(ticker, StocksType.NEWS);
        break;
      case "f":
        stock = StocksFactory.buildStocks(ticker, StocksType.FINANCIAL);
        break;
      case "e":
        stock = StocksFactory.buildStocks(ticker, StocksType.EARNING);
        break;
      default:
        throw new IllegalStateException("Invalid type");
    }

    stock.loadStocks(ticker);
    return stock.toString();
  }

}
