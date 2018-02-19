package model.technical_indicators.ema;

import org.jfree.data.time.Minute;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import model.technical_indicators.TechIndElement;

public class EMAElement implements TechIndElement {

  private double value;
  private Minute minute;
  private Date date;

  public EMAElement(String date, JSONObject json) {
    value = json.getDouble("EMA");
    String[] dateArray = date.split("-");
    int year = Integer.parseInt(dateArray[0]);
    int month = Integer.parseInt(dateArray[1]);
    int day = Integer.parseInt(dateArray[2]);
    this.minute = new Minute(0, 16, day, month, year);
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day, 16, 0);
    this.date = cal.getTime();
  }

  @Override
  public Minute getX() {
    return minute;
  }

  @Override
  public double getY() {
    return value;
  }

  @Override
  public Date getDate() {
    return date;
  }
}
