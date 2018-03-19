package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

import model.Quote;
import model.related.Related;
import view.util.NoEditCellsTableModel;

public class RelatedPanel extends JPanel {

  private Related related;
  private ArrayList<Quote> quotes;

  private JScrollPane scrollPane;
  private JTable table;
  private JLabel label;
  private JButton refreshButton;

  private Vector rowData;
  private Vector colNamesVector;

  public RelatedPanel() {
    super();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    related = new Related();
    related.fillRelatedStocks();
    quotes = related.getTop7Quotes();

    rowData = new Vector();
    for (Quote quote : quotes) {
      String ticker = quote.getSymbol();
      String name = quote.getName();
      double price = quote.getCurrentPrice();
      double open = quote.getOpen();
      double high = quote.getHigh();
      double low = quote.getLow();
      double change = quote.getChange();
      double changePct = quote.getChangePct();
      String changeStr = change + "(" + changePct + ")";
      Vector colData = new Vector(Arrays.asList(ticker, name, price, open, high, low, changeStr));
      rowData.add(colData);
    }

    String[] colNames = {"Ticker", "Name", "Price", "Open", "High", "Low", "Change"};
    colNamesVector = new Vector(Arrays.asList(colNames));

    NoEditCellsTableModel tableModel = new NoEditCellsTableModel(rowData, colNamesVector);

    table = new JTable(tableModel);
    scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    JPanel labelAndButton = new JPanel(new FlowLayout());
    labelAndButton.setPreferredSize(new Dimension(500, 50));
    labelAndButton.setSize(500, 50);

    label = new JLabel("Suggested Stocks For You");
    label.setSize(300, 40);
    label.setPreferredSize(new Dimension(300, 40));

    refreshButton = new JButton("Refresh");
    refreshButton.setSize(100, 40);
    refreshButton.setPreferredSize(new Dimension(100, 40));

    add(labelAndButton, BorderLayout.NORTH);

    setListeners();
  }

  public void setListeners() {
    refreshButton.addActionListener(e -> {
      NoEditCellsTableModel tableModel = (NoEditCellsTableModel) table.getModel();
      related.fillRelatedStocks();
      quotes = related.getTop7Quotes();
      for (int i = 0; i < tableModel.getRowCount(); i++) {
        tableModel.removeRow(i);
      }
      for (Quote quote : quotes) {
        String ticker = quote.getSymbol();
        String name = quote.getName();
        double price = quote.getCurrentPrice();
        double open = quote.getOpen();
        double high = quote.getHigh();
        double low = quote.getLow();
        double change = quote.getChange();
        double changePct = quote.getChangePct();
        String changeStr = change + "(" + changePct + ")";
        Vector colData = new Vector(Arrays.asList(ticker, name, price, open, high, low, changeStr));
        tableModel.addRow(colData);
      }
    });
  }

}
