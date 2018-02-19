package model.sector;

import org.json.JSONObject;

public class Sector {

  private String utils;
  private String healthCare;
  private String realEstate;
  private String telecom;
  private String consumerStaple;
  private String industrial;
  private String finance;
  private String infoTech;
  private String energy;
  private String material;
  private String consumerDisc;

  public Sector(JSONObject json) {
    utils = json.getString("Utilities");
    healthCare = json.getString("Health Care");
    realEstate = json.getString("Real Estate");
    telecom = json.getString("Telecommunication Services");
    consumerStaple = json.getString("Consumer Staples");
    industrial = json.getString("Industrial");
    finance = json.getString("Financials");
    infoTech = json.getString("Information Technology");
    energy = json.getString("Energy");
    material = json.getString("Materials");
    consumerDisc = json.getString("Consumer Discretionary");
  }

//  public String toString() {
//    String result = "";
//    result +=
//  }

}
