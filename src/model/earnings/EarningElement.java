package model.earnings;

import org.json.JSONObject;

public class EarningElement {

  private double actualEPS;
  private double consensusEPS;
  private double estimatedEPS;
  private String announceTime;
  private int numOfEstimates;
  private double epsSurpriseDollar;
  private String reportDate;
  private String fiscalPeriod;
  private String fiscalEndDate;

  public EarningElement(JSONObject json) {
    actualEPS = json.getDouble("actualEPS");
    consensusEPS = json.getDouble("consensusEPS");
    estimatedEPS = json.getDouble("estimatedEPS");
    announceTime = json.getString("announceTime");
    numOfEstimates = json.getInt("numberOfEstimates");
    epsSurpriseDollar = json.getDouble("EPSSurpriseDollar");
    reportDate = json.getString("EPSReportDate");
    fiscalPeriod = json.getString("fiscalPeriod");
    fiscalEndDate = json.getString("fiscalEndDate");
  }

  public String toString() {
    return "Actual EPS: " + actualEPS + "\n" +
            "Estimated EPS: " + estimatedEPS + "\n" +
            "Surprise: " + epsSurpriseDollar + "\n" +
            "Announce Time: " + announceTime + "\n" +
            "Number or Estimates: " + numOfEstimates + "\n" +
            "Report Date: " + reportDate + "\n" +
            "Fiscal Period: " + fiscalPeriod + "\n" +
            "Fiscal End Date: " + fiscalEndDate + "\n\n";
  }

}
