package view.notesviews;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

import model.database.H2Database;
import model.database.notes.Note;
import util.ClearFieldOnClick;
import view.util.NoEditCellsTableModel;

public class NotesPanel extends JPanel {

  private H2Database database;
  private ArrayList<Note> notes;
  private JScrollPane scrollPane;
  private JTable table;
  private JTextField titleField;
  private JTextField dateField;
  private JButton searchButton;

  private JButton newNoteButton;
  private JButton openNoteButton;
  private JButton deleteNoteButton;

  private Vector rowData;
  private Vector colNamesVector;

  public NotesPanel() {
    super();
    database = new H2Database();
    setPreferredSize(new Dimension(500, 500));
    setSize(500, 500);
    setLayout(new BorderLayout());

    notes = new ArrayList<>();
    notes = database.getAllNoteDetails();
    rowData = new Vector();
    for (Note note : notes) {
      int noteID = note.getNoteID();
      String title = note.getTitle();
      LocalDate dateCreated = note.getDateCreated();
      LocalDate dateUpdated = note.getDateUpdated();
      Vector colData = new Vector(Arrays.asList(noteID, title, dateCreated, dateUpdated));
      rowData.add(colData);
    }

    String[] colNames = {"NoteID", "Title", "Date Created", "Date Updated"};
    colNamesVector = new Vector(Arrays.asList(colNames));

    NoEditCellsTableModel tableModel = new NoEditCellsTableModel(rowData, colNamesVector);

    table = new JTable(tableModel);
    table.setRowSelectionAllowed(true);
    scrollPane = new JScrollPane(table);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane, BorderLayout.CENTER);

    JPanel fieldsAndButton = new JPanel(new FlowLayout());
    fieldsAndButton.setPreferredSize(new Dimension(500, 50));
    fieldsAndButton.setSize(500, 50);

    titleField = new JTextField("Search Title");
    titleField.setSize(175, 40);
    titleField.setPreferredSize(new Dimension(175, 40));
    titleField.addMouseListener(new ClearFieldOnClick(titleField));
    fieldsAndButton.add(titleField);

    dateField = new JTextField("Search Date");
    dateField.setSize(175, 40);
    dateField.setPreferredSize(new Dimension(175, 40));
    dateField.addMouseListener(new ClearFieldOnClick(dateField));
    fieldsAndButton.add(dateField);

    searchButton = new JButton("Search");
    searchButton.setSize(100, 40);
    searchButton.setPreferredSize(new Dimension(100, 40));
    fieldsAndButton.add(searchButton);

    add(fieldsAndButton, BorderLayout.NORTH);

    JPanel buttonsPanel = new JPanel(new FlowLayout());
    buttonsPanel.setPreferredSize(new Dimension(500, 50));
    setSize(500, 50);

    newNoteButton = new JButton("New Note");
    newNoteButton.setPreferredSize(new Dimension(150, 40));
    newNoteButton.setSize(150, 40);
    buttonsPanel.add(newNoteButton);

    openNoteButton = new JButton("Open Note");
    openNoteButton.setPreferredSize(new Dimension(150, 40));
    openNoteButton.setSize(150, 40);
    buttonsPanel.add(openNoteButton);

    deleteNoteButton = new JButton("Delete Note");
    deleteNoteButton.setPreferredSize(new Dimension(150, 40));
    deleteNoteButton.setSize(150, 40);
    buttonsPanel.add(deleteNoteButton);

    add(buttonsPanel, BorderLayout.SOUTH);

    setListeners();
  }

  public void setListeners() {
    searchButton.addActionListener(e -> {
      NoEditCellsTableModel tableModel = (NoEditCellsTableModel) table.getModel();
      String searchTitle = titleField.getText();
      String searchDate = dateField.getText();
      for (int row = 0; row < tableModel.getRowCount(); row++) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String rowDateCreated = ((LocalDate) tableModel.getValueAt(row, 2)).format(formatter);
        String rowDateUpdated = ((LocalDate) tableModel.getValueAt(row, 3)).format(formatter);
        String rowTitle = (String) tableModel.getValueAt(row, 1);
        if (rowDateCreated.equals(searchDate) || rowDateUpdated.equals(searchDate)) {
          table.addRowSelectionInterval(row, row);
        }
        if (rowTitle.equalsIgnoreCase(searchTitle)) {
          table.addRowSelectionInterval(row, row);
        }
      }
    });

    newNoteButton.addActionListener(e -> {
      OpenNotePanel newNotePanel = new OpenNotePanel(database, this);
      JFrame noteFrame = new JFrame();
      noteFrame.setSize(400, 400);
      noteFrame.add(newNotePanel);
      noteFrame.setVisible(true);
    });

    openNoteButton.addActionListener(e -> {
      NoEditCellsTableModel tableModel = (NoEditCellsTableModel) table.getModel();
      int[] selectedRows = table.getSelectedRows();
      int nodeID = (int) tableModel.getValueAt(selectedRows[0], 0);
      String title = (String) tableModel.getValueAt(selectedRows[0], 1);
      OpenNotePanel openNotePanel = new OpenNotePanel(database, this, nodeID, title);
      JFrame noteFrame = new JFrame();
      noteFrame.setSize(400, 400);
      noteFrame.add(openNotePanel);
      noteFrame.setVisible(true);
    });

    deleteNoteButton.addActionListener(e -> {
      NoEditCellsTableModel tableModel = (NoEditCellsTableModel) table.getModel();
      int row = table.getSelectedRow();
      int nodeID = (int) tableModel.getValueAt(row, 0);
      database.deleteNote(nodeID);
      tableModel.removeRow(row);
    });

    NotesPanel notesPanel = this;
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        JTable mouseTable = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = mouseTable.rowAtPoint(point);
        NoEditCellsTableModel tableModel = (NoEditCellsTableModel) table.getModel();
        if (e.getClickCount() == 2) {
          int nodeID = (int) tableModel.getValueAt(row, 0);
          String title = (String) tableModel.getValueAt(row, 1);
          OpenNotePanel openNotePanel = new OpenNotePanel(database, notesPanel, nodeID, title);
          JFrame noteFrame = new JFrame();
          noteFrame.setSize(400, 400);
          noteFrame.add(openNotePanel);
          noteFrame.setVisible(true);
        }
      }
    });
  }

  public void resetTableModel() {
    notes = new ArrayList<>();
    notes = database.getAllNoteDetails();
    rowData = new Vector();
    for (Note note : notes) {
      int noteID = note.getNoteID();
      String title = note.getTitle();
      LocalDate dateCreated = note.getDateCreated();
      LocalDate dateUpdated = note.getDateUpdated();
      Vector colData = new Vector(Arrays.asList(noteID, title, dateCreated, dateUpdated));
      rowData.add(colData);
    }
    NoEditCellsTableModel tableModel = new NoEditCellsTableModel(rowData, colNamesVector);
    table.setModel(tableModel);
  }

}
