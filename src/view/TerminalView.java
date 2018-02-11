package view;

import java.awt.*;

import javax.swing.*;

public class TerminalView extends JFrame {

  private TerminalPanel tp;

  public TerminalView() {
    super();
    setSize(500, 500);
    setPreferredSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setBackground(Color.BLACK);

    tp = new TerminalPanel();
    add(tp);
    getRootPane().setDefaultButton(tp.getRunButton());
    pack();
    setVisible(true);
  }

}
