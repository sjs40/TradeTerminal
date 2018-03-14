package view;

import java.awt.*;

import javax.swing.*;

import model.twitter.Twitter;
import util.ClearFieldOnClick;

public class TwitterPanel extends JPanel {

  private JTextField searchField;
  private JButton searchButton;
  private JScrollPane consolePane;
  private JTextPane console;
  private Twitter twitter;

  public TwitterPanel() {
    super();
    twitter = new Twitter();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    console = new JTextPane();
    console.setSize(480, 410);
    console.setPreferredSize(new Dimension(480, 410));
    console.setText("Enter the ticker to search on Twitter above.");

    consolePane = new JScrollPane(console);
    consolePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(consolePane, BorderLayout.CENTER);

    JPanel fieldAndButton = new JPanel(new FlowLayout());
    fieldAndButton.setPreferredSize(new Dimension(500, 50));
    fieldAndButton.setSize(500, 50);

    searchField = new JTextField("Enter Ticker to Search");
    searchField.setSize(300, 40);
    searchField.setPreferredSize(new Dimension(300, 40));
    searchField.addMouseListener(new ClearFieldOnClick(searchField));
    fieldAndButton.add(searchField);

    searchButton = new JButton("Search");
    searchButton.setSize(100, 40);
    searchButton.setPreferredSize(new Dimension(100, 40));
    fieldAndButton.add(searchButton);

    add(fieldAndButton, BorderLayout.NORTH);
    setListeners();
  }

  public void setListeners() {
    searchButton.addActionListener(e -> {
      String searchTerm = searchField.getText();
      try {
        String result = twitter.getSearchResults(searchTerm);
        console.setText(result);
      } catch (IllegalArgumentException iae) {
        JOptionPane.showMessageDialog(this, "This is not a valid ticker",
                "Twitter Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  public JButton getSearchButton() {
    return searchButton;
  }
}
