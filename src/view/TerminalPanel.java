package view;

import java.awt.*;

import javax.swing.*;

import util.Utils;

public class TerminalPanel extends JPanel {

  private JTextPane console;
  private JScrollPane consolePane;
  private JTextField runField;
  private JButton runButton;

  public TerminalPanel() {
    super();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    console = new JTextPane();
    console.setSize(480, 410);
    console.setPreferredSize(new Dimension(480, 410));
    console.setBackground(Color.BLACK);
    console.setForeground(Color.GREEN);
    console.setText("Welcome to TradeTerminal.\nEnter ");
    consolePane = new JScrollPane(console);
    consolePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(consolePane);

    JPanel fieldAndButton = new JPanel(new FlowLayout());
    fieldAndButton.setPreferredSize(new Dimension(500, 50));
    fieldAndButton.setSize(500, 50);

    runField = new JTextField();
    runField.setSize(380, 40);
    runField.setPreferredSize(new Dimension(380, 40));
    fieldAndButton.add(runField);

    runButton = new JButton("Run");
    runButton.setSize(100, 40);
    runButton.setPreferredSize(new Dimension(100, 40));
    fieldAndButton.add(runButton);

    add(fieldAndButton);
    setListeners();
  }

  public void setListeners() {
    runButton.addActionListener(e -> {
      String consoleText = console.getText();
      String runText = runField.getText();
      String command = runText.split(" ")[0];
      String ticker = runText.split(" ")[1];
      String result = Utils.getResult(ticker, command);
      consoleText += result + "\n";
      runField.setText("");
      console.setText(consoleText);
    });
  }

  public JButton getRunButton() {
    return runButton;
  }
}
