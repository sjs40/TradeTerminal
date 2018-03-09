package key;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {

  public static String getKey() {
    try {
      Properties prop = new Properties();
      InputStream is = new FileInputStream("/Users/samserio/Documents/personal_coding/TradeTerminal/src/trade.properties");
      prop.load(is);
      return prop.getProperty("Key");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "None";
  }

  public static String getUser() {
    try {
      Properties prop = new Properties();
      InputStream is = new FileInputStream("/Users/samserio/Documents/personal_coding/TradeTerminal/src/trade.properties");
      prop.load(is);
      return prop.getProperty("user");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "None";
  }

  public static String getPassword() {
    try {
      Properties prop = new Properties();
      InputStream is = new FileInputStream("/Users/samserio/Documents/personal_coding/TradeTerminal/src/trade.properties");
      prop.load(is);
      return prop.getProperty("password");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "None";
  }

}
