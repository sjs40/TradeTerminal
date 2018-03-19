import model.database.H2Database;
import model.related.Related;

public class DBTest {
  public static void main(String[] args) {
    Related related = new Related();
    related.fillRelatedStocks();
    System.out.println(related.getRelatedStocksString());
    System.out.println(related.getStocksHeldString());
  }
}
