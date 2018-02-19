package key;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Key {

  public static String getKey() {
    try {
      Properties prop = new Properties();
      InputStream is = new FileInputStream("trade.properties");
      prop.load(is);
      return prop.getProperty("Key");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "None";
  }

}
