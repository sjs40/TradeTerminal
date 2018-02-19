package model.chartdata;

import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.util.ArrayList;

import model.stockdata.DataFunction;
import model.stockdata.StockData;
import model.stockdata.StockSeries;
import model.technical_indicators.ema.EMA;
import model.technical_indicators.sma.SMA;

public class ChartData {

  private DataFunction function;
  private String ticker;
  private StockSeries stockSeries;
  private TimeSeries timeSeries;

  private ArrayList<ChartDataElement> elements;

  public ChartData(DataFunction function, String ticker) {
    this.function = function;
    this.ticker = ticker;
    stockSeries = new StockSeries(function, ticker);
  }

  public XYDataset createDataset() {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    timeSeries = new TimeSeries("Price", Minute.class);
    for (StockData data : stockSeries.getData()) {
      String[] dateArray = data.getX().split("-");
      int year = Integer.parseInt(dateArray[0]);
      int month = Integer.parseInt(dateArray[1]);
      int day = Integer.parseInt(dateArray[2]);
      Minute date = new Minute(0, 16, day, month ,year);
      timeSeries.add(date, data.getY());
    }
    dataset.addSeries(timeSeries);
    RegularTimePeriod earliest = timeSeries.getDataItem(0).getPeriod();
    SMA sma = new SMA(ticker, function, "30", earliest);
    EMA ema = new EMA(ticker, function, "30", earliest);
    dataset.addSeries(sma.getTimeSeries());
    dataset.addSeries(ema.getTimeSeries());
    return dataset;
  }

}
