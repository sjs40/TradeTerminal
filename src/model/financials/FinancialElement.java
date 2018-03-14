package model.financials;

import org.json.JSONObject;

import java.math.BigInteger;

import util.Utils;

public class FinancialElement {

  private String reportDate;
  private BigInteger grossProfit;
  private BigInteger costOfRev;
  private BigInteger operatingRev;
  private BigInteger totalRev;
  private BigInteger operatingIncome;
  private BigInteger netIncome;
  private BigInteger rAndD;
  private BigInteger operatingExpense;
  private BigInteger currentAssets;
  private BigInteger totalAssets;
  private BigInteger totalLiabilities;
  private BigInteger currentCash;
  private BigInteger currentDebt;
  private BigInteger totalCash;
  private BigInteger totalDebt;
  private BigInteger shareholderEquity;
  private BigInteger cashChange;
  private BigInteger cashFlow;

  public FinancialElement(JSONObject json) {
    reportDate = json.getString("reportDate");
    grossProfit = json.getBigInteger("grossProfit");
    costOfRev = json.getBigInteger("costOfRevenue");
    operatingRev = json.getBigInteger("operatingRevenue");
    totalRev = json.getBigInteger("totalRevenue");
    operatingIncome = json.getBigInteger("operatingIncome");
    netIncome = json.getBigInteger("netIncome");
    rAndD = json.getBigInteger("researchAndDevelopment");
    operatingExpense = json.getBigInteger("operatingExpense");
    currentAssets = json.getBigInteger("currentAssets");
    totalAssets = json.getBigInteger("totalAssets");
    totalLiabilities = json.getBigInteger("totalLiabilities");
    currentCash = json.getBigInteger("currentCash");
    currentDebt = json.getBigInteger("currentDebt");
    totalCash = json.getBigInteger("totalCash");
    totalDebt = json.getBigInteger("totalDebt");
    shareholderEquity = json.getBigInteger("shareholderEquity");
    cashChange = json.getBigInteger("cashChange");
    cashFlow = json.getBigInteger("cashFlow");
  }

  public String toString() {
    return "Date: " + reportDate + "\n" +
            "Gross Profit: " + Utils.currencyFormat(grossProfit) + "\n" +
            "Cost of Revenue: " + Utils.currencyFormat(costOfRev) + "\n" +
            "Operating Revenue: " + Utils.currencyFormat(operatingRev) + "\n" +
            "Total Revenue: " + Utils.currencyFormat(totalRev) + "\n" +
            "Operating Income: " + Utils.currencyFormat(operatingIncome) + "\n" +
            "Net Income: " + Utils.currencyFormat(netIncome) + "\n" +
            "R&D: " + Utils.currencyFormat(rAndD) + "\n" +
            "Operating Expense: " + Utils.currencyFormat(operatingExpense) + "\n" +
            "Current Assets: " + Utils.currencyFormat(currentAssets) + "\n" +
            "Total Assets: " + Utils.currencyFormat(totalAssets) + "\n" +
            "Total Liabilities: " + Utils.currencyFormat(totalLiabilities) + "\n" +
            "Current Cash: " + Utils.currencyFormat(currentCash) + "\n" +
            "Current Debt: " + Utils.currencyFormat(currentDebt) + "\n" +
            "Total Cash: " + Utils.currencyFormat(totalCash) + "\n" +
            "Total Debt: " + Utils.currencyFormat(totalDebt) + "\n" +
            "Shareholder Equity: " + Utils.currencyFormat(shareholderEquity) + "\n" +
            "Cash Change: " + Utils.currencyFormat(cashChange) + "\n" +
            "Cash Flow: " + Utils.currencyFormat(cashFlow) + "\n\n";
  }

}
