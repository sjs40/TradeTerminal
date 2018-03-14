package view.notesviews;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.database.DB;
import model.database.notes.Note;

public class OpenNotePanel extends JPanel {

  private DB database;
  private NotesPanel notesPanel;

  private JTextArea textArea;
  private JScrollPane scrollPane;
  private JTextField noteIDField;
  private JTextField titleField;
  private JButton saveButton;

  private Integer noteID;
  private String title;

  private boolean newNote = true;

  public OpenNotePanel(DB database, NotesPanel notesPanel, int noteID, String title) {
    super();
    this.notesPanel = notesPanel;
    this.noteID = noteID;
    this.title = title;
    this.database = database;
    initPanel();
  }

  public OpenNotePanel(DB database, NotesPanel notesPanel) {
    super();
    this.notesPanel = notesPanel;
    this.database = database;
    initPanel();
  }

  private void initPanel() {
    setPreferredSize(new Dimension(400, 400));
    setSize(400, 400);
    setLayout(new BorderLayout());

    textArea = new JTextArea();
    scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 400));
    scrollPane.setSize(400, 400);
    add(scrollPane, BorderLayout.CENTER);

    JPanel fieldsAndButton = new JPanel(new FlowLayout());
    fieldsAndButton.setPreferredSize(new Dimension(400, 32));
    fieldsAndButton.setSize(400, 32);

    noteIDField = new JTextField();
    noteIDField.setEnabled(false);
    noteIDField.setPreferredSize(new Dimension(140, 32));
    noteIDField.setSize(140, 32);
    fieldsAndButton.add(noteIDField);

    titleField = new JTextField();
    titleField.setPreferredSize(new Dimension(140, 32));
    titleField.setSize(140, 32);
    fieldsAndButton.add(titleField);

    saveButton = new JButton("Save");
    saveButton.setPreferredSize(new Dimension(80, 32));
    saveButton.setSize(80, 32);
    fieldsAndButton.add(saveButton);

    add(fieldsAndButton, BorderLayout.NORTH);

    Note note;
    if (noteID != null) {
      noteIDField.setText(String.valueOf(noteID));
      note = database.getNoteTextByID(noteID);
      textArea.setText(note.getText());
      newNote = false;
    }
    if (title != null) {
      titleField.setText(title);
    }

    setListener();

  }

  public void setListener() {
    saveButton.addActionListener(e -> {
      if (newNote) {
        String newTitle = titleField.getText();
        String newText = textArea.getText();
        database.addNote(newTitle, newText);
        notesPanel.resetTableModel();
      } else {
        String newTitle = titleField.getText();
        String newText = textArea.getText();
        database.updateNote(noteID, newTitle, newText);
        notesPanel.resetTableModel();
      }
    });
  }

}
