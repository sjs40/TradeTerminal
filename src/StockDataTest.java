import org.json.JSONObject;

import java.io.IOException;

import model.stockdata.DataFunction;
import model.stockdata.StockSeries;
import util.JsonReader;

public class StockDataTest {

  public static void main(String[] args) throws IOException {
    StockSeries series = new StockSeries(DataFunction.WEEKLY, "AAPL");
    System.out.println(series.toString());

  }
}
