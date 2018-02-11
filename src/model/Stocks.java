package model;

public interface Stocks {

  String URL_1 = "https://api.iextrading.com/1.0/stock/";

  void loadStocks(String ticker);

  String toString();

}
