import model.crypto.coin.CoinList;
import model.crypto.pair.TopPairs;
import model.crypto.price.CoinPrice;
import model.stockdata.DataFunction;
import view.charts.LineChart;

public class CryptoTest {

  public static void main(String[] args) {
//    CoinList list = new CoinList();
//    list.keepTop20();
//    System.out.println(list.toStringFull());
//
//    CoinPrice price = new CoinPrice("BTC", "USD", "EUR");
//    System.out.println(price.toString());
//
//    TopPairs pairs = new TopPairs("BTC");
//    System.out.println(pairs.toStringFull());

    LineChart chart = new LineChart(DataFunction.DAILY_ADJUST, "aapl");
  }

}
