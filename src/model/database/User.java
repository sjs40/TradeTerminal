package model.database;

import java.util.ArrayList;

public class User {

  private String username;
  private double balance;
  private ArrayList<Trade> transactions;

  public User(String username, double balance, ArrayList<Trade> transactions) {
    this.username = username;
    this.balance = balance;
    this.transactions = transactions;
  }



}
