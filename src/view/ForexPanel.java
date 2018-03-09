package view;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import model.forex.Forex;

public class ForexPanel extends JPanel {

  private JScrollPane scrollPane;
  private JTable table;
  private Forex forex;

  public ForexPanel() {
    super();
    setSize(500, 500);
    setPreferredSize(new Dimension(500, 500));

    forex = new Forex();
    Map<String, Double> rawData = forex.getPairsToRates();

    String[] columnNames = {"Pair", "Rate"};

    Object[][] data = new Object[rawData.size()][];
    int s = 0;
    for (Map.Entry<String, Double> pair : rawData.entrySet()) {
      Object[] row = {pair.getKey(), pair.getValue()};
      data[s] = row;
    }
    table = new JTable(data, columnNames);
    scrollPane = new JScrollPane(table);
    add(scrollPane);
  }

}
