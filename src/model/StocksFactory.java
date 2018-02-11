package model;

import model.earnings.Earning;
import model.financials.Financial;
import model.news.News;

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

}
