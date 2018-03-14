package view.util;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NoEditCellsTableModel extends DefaultTableModel {

  public NoEditCellsTableModel(Vector vector1, Vector vector2) {
    super(vector1, vector2);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }

}
