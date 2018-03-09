package view;

import java.awt.*;

import javax.swing.*;

import util.Utils;

public class MainPanel extends JPanel {

  private JTextField stockField;
  private JTextField commandField;
  private JButton enterButton;
  private JScrollPane consolePane;
  private JTextPane console;

  public MainPanel() {
    super();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    console = new JTextPane();
    console.setSize(480, 410);
    console.setPreferredSize(new Dimension(480, 410));
    console.setText("Welcome to TraderTerminal.\nEnter a ticker and command above.");

    consolePane = new JScrollPane(console);
    consolePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(consolePane, BorderLayout.CENTER);

    JPanel fieldsAndButton = new JPanel(new FlowLayout());
    fieldsAndButton.setPreferredSize(new Dimension(500, 50));
    fieldsAndButton.setSize(500, 50);

    stockField = new JTextField();
    stockField.setSize(175, 40);
    stockField.setPreferredSize(new Dimension(175, 40));
    fieldsAndButton.add(stockField);

    commandField = new JTextField();
    commandField.setSize(175, 40);
    commandField.setPreferredSize(new Dimension(175, 40));
    fieldsAndButton.add(commandField);

    enterButton = new JButton("Enter");
    enterButton.setSize(100, 40);
    enterButton.setPreferredSize(new Dimension(100, 40));
    fieldsAndButton.add(enterButton);

    add(fieldsAndButton, BorderLayout.NORTH);
    setListeners();
  }

  public void setListeners() {
    enterButton.addActionListener(e -> {
      String ticker = stockField.getText();
      String command = commandField.getText();
      String result = Utils.getResult(ticker, command);
      console.setText(result);
    });
  }

  public JButton getEnterButton() {
    return enterButton;
  }
}
