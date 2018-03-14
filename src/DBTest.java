import model.database.H2Database;

public class DBTest {
  public static void main(String[] args) {
    H2Database database = new H2Database();
    database.getConn();
    database.createTables();
  }
}
