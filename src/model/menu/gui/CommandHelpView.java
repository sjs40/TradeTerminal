package model.menu.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.menu.content.CommandHelp;

public class CommandHelpView extends JFrame {

  public CommandHelpView() {
    super("Commands");
    setPreferredSize(new Dimension(500, 300));
    setSize(500, 300);
    add(new CommandHelpPanel());
    pack();
    setVisible(true);
  }

}

class CommandHelpPanel extends JPanel {

  private JTextPane textPane;

  CommandHelpPanel() {
    super();
    setPreferredSize(new Dimension(500, 300));
    setSize(500, 300);

    textPane = new JTextPane();
    textPane.setEnabled(false);
    textPane.setSize(500, 300);
    textPane.setPreferredSize(new Dimension(500, 300));
    textPane.setText(CommandHelp.getCommandHelp());
    add(textPane);
  }

}
