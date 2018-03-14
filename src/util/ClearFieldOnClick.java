package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ClearFieldOnClick extends MouseAdapter {

  private JTextField field;

  public ClearFieldOnClick(JTextField field) {
    this.field = field;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    field.setText("");
  }

  @Override
  public void mousePressed(MouseEvent e) {
    field.setText("");
  }

}
