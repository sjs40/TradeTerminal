package model.chartdata;

import org.json.JSONObject;

public class ChartDataElementDay implements ChartDataElement {

  private String date;
  private String minute;
  private String label;
  private double high;
  private double low;
  private double average;
  private int volume;
  private int numOfTrades;
  private double change;

  public ChartDataElementDay(JSONObject json) {
    date = json.getString("date");
    minute = json.getString("minute");
    label = json.getString("label");
    high = json.getDouble("high");
    low = json.getDouble("low");
    average = json.getDouble("average");
    volume = json.getInt("volume");
    numOfTrades = json.getInt("numberOfTrades");
    change = json.getDouble("changeOverTime");
  }

  public double getY() {
    return average;
  }

  public String getX() {
    return minute;
  }

}
