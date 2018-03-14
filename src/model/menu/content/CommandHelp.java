package model.menu.content;

public class CommandHelp {

  public static final String Q      = "  q -------------- Returns quote for the ticker.";
  public static final String COMP   = "  comp ----------- Returns company information for the ticker.";
  public static final String NEWS   = "  news ----------- Returns top news for the ticker.";
  public static final String FIN    = "  fin ------------ Returns financial information for the ticker.";
  public static final String EAR    = "  ear ------------ Returns earning information for the ticker.";
  public static final String CHART  = "  chart [period] - Returns chart of the ticker for the period.";
  public static final String PERIOD = "    --- period: intra, day, week, month";

  public static String getCommandHelp() {
    return Q + "\n" + COMP + "\n" + NEWS + "\n" + FIN + "\n" + EAR + "\n" + CHART + "\n" + PERIOD;
  }

}
