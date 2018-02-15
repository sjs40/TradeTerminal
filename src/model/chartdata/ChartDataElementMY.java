package model.chartdata;

import org.json.JSONObject;

public class ChartDataElementMY implements ChartDataElement {

  private String date;
  private double open;
  private double high;
  private double low;
  private double close;
  private double change;
  private double changePct;
  private double vwap;
  private String label;

  public ChartDataElementMY(JSONObject json) {
    date = json.getString("date");
    open = json.getDouble("open");
    high = json.getDouble("high");
    low = json.getDouble("low");
    close = json.getDouble("close");
    change = json.getDouble("change");
    changePct = json.getDouble("changePercent");
    vwap = json.getDouble("vwap");
    label = json.getString("label");
  }

  public double getY() {
    return close;
  }

  public String getX() {
    return date;
  }

}
