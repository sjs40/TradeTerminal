import com.jtattoo.plaf.JTattooUtilities;

import org.json.JSONObject;

import java.io.IOException;

import javax.swing.*;

import model.Company;
import model.Quote;
import model.earnings.Earning;
import model.financials.Financial;
import model.news.News;
import view.TerminalView;

public class Test {
  public static void main(String[] args) {
//    Quote stock = new Quote("aapl");
//    System.out.println(stock.getFullPrice());
//    Company company = new Company("aapl");
//    System.out.println(company.toString());
//    News news = new News("aapl");
//    System.out.println(news.toString());
//    Financial financial = new Financial("aapl");
//    System.out.println(financial.toString());
//    Earning earning = new Earning("aapl");
//    System.out.println(earning.toString());

    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    TerminalView view = new TerminalView();
  }
}
