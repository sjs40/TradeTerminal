package view;

import java.awt.*;

import javax.swing.*;

import model.bible.Bible;

public class BiblePanel extends JPanel {

  private Bible bible;
  private JTextField bookField;
  private JTextField chapterField;
  private JButton enterButton;
  private JScrollPane consolePane;
  private JTextPane console;

  public BiblePanel() {
    super();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    console = new JTextPane();
    console.setSize(480, 410);
    console.setPreferredSize(new Dimension(480, 410));
    console.setText("Enter a Book and Chapter above.");

    consolePane = new JScrollPane(console);
    consolePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(consolePane, BorderLayout.CENTER);

    JPanel fieldsAndButton = new JPanel(new FlowLayout());
    fieldsAndButton.setPreferredSize(new Dimension(500, 50));
    fieldsAndButton.setSize(500, 50);

    bookField = new JTextField();
    bookField.setSize(175, 40);
    bookField.setPreferredSize(new Dimension(175, 40));
    fieldsAndButton.add(bookField);

    chapterField = new JTextField();
    chapterField.setSize(175, 40);
    chapterField.setPreferredSize(new Dimension(175, 40));
    fieldsAndButton.add(chapterField);

    enterButton = new JButton("Enter");
    enterButton.setSize(100, 40);
    enterButton.setPreferredSize(new Dimension(100, 40));
    fieldsAndButton.add(enterButton);

    add(fieldsAndButton, BorderLayout.NORTH);
    setListeners();
  }

  public void setListeners() {
    enterButton.addActionListener(e -> {
      String book = bookField.getText();
      String chapter = chapterField.getText();
      bible = new Bible(book, chapter);
      bible.setOutput();
      console.setText(bible.getOutput());
    });
  }

  public JButton getEnterButton() {
    return enterButton;
  }
}
