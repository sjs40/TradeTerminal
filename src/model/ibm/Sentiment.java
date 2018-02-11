package model.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

import org.json.JSONObject;

public class Sentiment {

  private AnalysisResults response;

  public Sentiment(AnalysisResults response) {
    this.response = response;
  }

  @Override
  public String toString() {
    JSONObject json = new JSONObject(response);
    JSONObject sentimentJson = json.getJSONObject("sentiment");
    JSONObject docJson = sentimentJson.getJSONObject("document");
    double score = docJson.getDouble("score");
    String label = docJson.getString("label");
    return "Sentiment: " + label + " (" + score + ")\n";
  }
}
