package view;

import org.jfree.chart.JFreeChart;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Quote;
import model.database.DB;
import model.database.Position;
import util.ClearFieldOnClick;

public class PortfolioPanel extends JPanel {

  private DB database;
  private ArrayList<Position> positions;
  private JScrollPane scrollPane;
  private JTable table;
  private JTextField tickerField;
  private JTextField amountField;
  private JButton executeButton;
  private JButton sellButton;

  private Vector rowData;
  private Vector colNamesVector;


  public PortfolioPanel() {
    super();
    database = new DB();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    positions = new ArrayList<>();
    positions = database.getAllPositions();
    rowData = new Vector();
    for (Position pos : positions) {
      int positionID = pos.getPositionID();
      String ticker = pos.getTicker();
      LocalDate date = pos.getDateFromSQLDate();
      double price = pos.getPrice();
      double currentPrice = pos.getCurrentPrice();
      int amount = pos.getAmount();
      double totalPricePurchased = price * amount;
      Vector colData = new Vector(Arrays.asList(positionID, ticker, date, price, currentPrice, amount, totalPricePurchased));
      rowData.add(colData);
    }


    String[] colNames = {"PositionID", "Ticker", "Date Purchased", "Price Purchased", "Current Price", "Amount Purchased", "Total Price Purchased"};
    colNamesVector = new Vector(Arrays.asList(colNames));

    DefaultTableModel tableModel = new DefaultTableModel(rowData, colNamesVector);


    table = new JTable(tableModel);
    scrollPane = new JScrollPane(table);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane, BorderLayout.CENTER);

    JPanel fieldsAndButton = new JPanel(new FlowLayout());
    fieldsAndButton.setPreferredSize(new Dimension(500, 50));
    fieldsAndButton.setSize(500, 50);

    tickerField = new JTextField("Ticker to Buy");
    tickerField.setSize(175, 40);
    tickerField.setPreferredSize(new Dimension(175, 40));
    tickerField.addMouseListener(new ClearFieldOnClick(tickerField));
    fieldsAndButton.add(tickerField);

    amountField = new JTextField("Amount to Buy");
    amountField.setSize(175, 40);
    amountField.setPreferredSize(new Dimension(175, 40));
    amountField.addMouseListener(new ClearFieldOnClick(amountField));
    fieldsAndButton.add(amountField);

    executeButton = new JButton("Execute");
    executeButton.setSize(100, 40);
    executeButton.setPreferredSize(new Dimension(100, 40));
    fieldsAndButton.add(executeButton);

    add(fieldsAndButton, BorderLayout.NORTH);

    JPanel sellPanel = new JPanel();
    sellPanel.setPreferredSize(new Dimension(500, 50));
    setSize(500, 50);

    sellButton = new JButton("Sell");
    sellButton.setSize(100, 40);
    sellButton.setPreferredSize(new Dimension(100, 40));
    sellPanel.add(sellButton);
    add(sellPanel, BorderLayout.SOUTH);

    setListeners();
  }

  public void setListeners() {
    executeButton.addActionListener(e -> {
      String ticker = tickerField.getText();
      try {
        int amount = Integer.parseInt(amountField.getText());
        Date date = Date.valueOf(LocalDate.now());
        Quote quote = new Quote(ticker);
        quote.loadStocks(ticker);
        double price = quote.getCurrentPrice();
        int lastPosID = rowData.isEmpty() ? 0 : (Integer) ((Vector) rowData.lastElement()).firstElement();
        database.addPosition(lastPosID + 1, ticker, date, price, amount);
        Position pos = new Position(lastPosID + 1, ticker, date, price, amount);
        updateRowData(pos);
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this,
                "Not a valid amount. Please enter an integer.",
                "Amount Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    sellButton.addActionListener(e -> {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      int[] rows = table.getSelectedRows();
      for (int i = 0; i < rows.length; i++) {
        int posID = (Integer) model.getValueAt(rows[i] - i, 0);
        database.deletePosition(posID);
        model.removeRow(rows[i]-i);
      }
    });
  }

  private void updateRowData(Position pos) {
    double totalPricePurchased = pos.getPrice() * pos.getAmount();
    Vector colData = new Vector(Arrays.asList(pos.getPositionID(), pos.getTicker(), pos.getDate(),
            pos.getPrice(), pos.getPrice(), pos.getAmount(), totalPricePurchased));
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.addRow(colData);
  }

}
