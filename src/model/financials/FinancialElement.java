package model.financials;

import org.json.JSONObject;

public class FinancialElement {

  private String reportDate;
  private int grossProfit;
  private int costOfRev;
  private int operatingRev;
  private int totalRev;
  private int operatingIncome;
  private int netIncome;
  private int rAndD;
  private int operatingExpense;
  private int currentAssets;
  private int totalAssets;
  private int totalLiabilities;
  private int currentCash;
  private int currentDebt;
  private int totalCash;
  private int totalDebt;
  private int shareholderEquity;
  private int cashChange;
  private int cashFlow;

  public FinancialElement(JSONObject json) {
    reportDate = json.getString("reportDate");
    grossProfit = json.getInt("grossProfit");
    costOfRev = json.getInt("costOfRevenue");
    operatingRev = json.getInt("operatingRevenue");
    totalRev = json.getInt("totalRevenue");
    operatingIncome = json.getInt("operatingIncome");
    netIncome = json.getInt("netIncome");
    rAndD = json.getInt("researchAndDevelopment");
    operatingExpense = json.getInt("operatingExpense");
    currentAssets = json.getInt("currentAssets");
    totalAssets = json.getInt("totalAssets");
    totalLiabilities = json.getInt("totalLiabilities");
    currentCash = json.getInt("currentCash");
    currentDebt = json.getInt("currentDebt");
    totalCash = json.getInt("totalCash");
    totalDebt = json.getInt("totalDebt");
    shareholderEquity = json.getInt("shareholderEquity");
    cashChange = json.getInt("cashChange");
    cashFlow = json.getInt("cashFlow");
  }

  public String toString() {
    return "Date: " + reportDate + "\n" +
            "Gross Profit: " + grossProfit + "\n" +
            "Cost of Revenue: " + costOfRev + "\n" +
            "Operating Revenue: " + operatingRev + "\n" +
            "Total Revenue: " + totalRev + "\n" +
            "Operating Income: " + operatingIncome + "\n" +
            "Net Income: " + netIncome + "\n" +
            "R&D: " + rAndD + "\n" +
            "Operating Expense: " + operatingExpense + "\n" +
            "Current Assets: " + currentAssets + "\n" +
            "Total Assets: " + totalAssets + "\n" +
            "Total Liabilities: " + totalLiabilities + "\n" +
            "Current Cash: " + currentCash + "\n" +
            "Current Debt: " + currentDebt + "\n" +
            "Total Cash: " + totalCash + "\n" +
            "Total Debt: " + totalDebt + "\n" +
            "Shareholder Equity: " + shareholderEquity + "\n" +
            "Cash Change: " + cashChange + "\n" +
            "Cash Flow: " + cashFlow + "\n\n";
  }

}
