package model.chartdata;

import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.chart.XYChart;
import util.JsonReader;

public class ChartData {

  private String url1 = "https://api.iextrading.com/1.0/stock/";
  private String url2 = "/chart/";
  private String range;
  private String ticker;
  private TimeSeries timeSeries;

  private ArrayList<ChartDataElement> elements;

  public ChartData(String range, String ticker) {
    this.range = range;
    this.ticker = ticker.toLowerCase();
    String url = url1 + this.ticker + url2 + range;
    try {
      JSONArray array = JsonReader.readJsonArrayFromUrl(url);
      elements = new ArrayList<>();
      for (int i = 0; i < array.length(); i++) {
        if (range.equals("1d")) {
          elements.add(new ChartDataElementDay(array.getJSONObject(i)));
        } else {
          elements.add(new ChartDataElementMY(array.getJSONObject(i)));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public XYDataset createDataset() {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    timeSeries = new TimeSeries("Price", Minute.class);
    if (range.equals("1d")) {
      for (ChartDataElement e : elements) {
        String[] minuteArray = e.getX().split(":");
        int hour = Integer.parseInt(minuteArray[0]);
        int min = Integer.parseInt(minuteArray[1]);
        Minute minute = new Minute(min, hour, 1, 1, 2018);
        timeSeries.add(minute, e.getY());
      }
    } else {
      for (ChartDataElement e : elements) {
        String[] dateArray = e.getX().split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        Minute minute = new Minute(0, 0, day, month, year);
        timeSeries.add(minute, e.getY());
      }
    }
    dataset.addSeries(timeSeries);
    return dataset;
  }

  public TimeSeries getTimeSeries() {
    return timeSeries;
  }
}
