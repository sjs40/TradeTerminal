package view;

import model.investopedia.Investopedia;
import model.twitter.Twitter;

public class InvestopediaTest {
  public static void main(String[] args) {
    Investopedia pedia = new Investopedia("EAGLES");
    System.out.println(pedia.getDefinition());
    Twitter twitter = new Twitter();
    System.out.println(twitter.getSearchResults("AAPL"));
  }

}
