package model.database.notes;

import java.time.LocalDate;

public class Note {

  private int noteID;
  private String title;
  private LocalDate dateCreated;
  private LocalDate dateUpdated;
  private String text;

  public Note(int noteID) {
    this.noteID = noteID;
  }

  public Note() { }

  public void setNoteID(int noteID) {
    this.noteID = noteID;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDateCreated(LocalDate date) {
    this.dateCreated = date;
  }

  public void setDateUpdated(LocalDate dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getNoteID() {
    return noteID;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public LocalDate getDateUpdated() {
    return dateUpdated;
  }

  public String getText() {
    return text;
  }

  public String getTitle() {
    return title;
  }

}
