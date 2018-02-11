package model.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

import org.json.JSONObject;

public class Emotion {

  private AnalysisResults response;

  public Emotion(AnalysisResults response) {
    this.response = response;
  }

  @Override
  public String toString() {
    JSONObject json = new JSONObject(response);
    JSONObject emotionJson = json.getJSONObject("emotion");
    JSONObject docJson = emotionJson.getJSONObject("docuement");
    JSONObject docEmotionJson = docJson.getJSONObject("emotion");
    String sadness = "Sadness: " + docEmotionJson.getDouble("sadness");
    String joy = "Joy: " + docEmotionJson.getDouble("joy");
    String fear = "Fear: " + docEmotionJson.getDouble("fear");
    String disgust = "Disgust: " + docEmotionJson.getDouble("disgust");
    String anger = "Anger: " + docEmotionJson.getDouble("anger");
    return sadness + "\n" + joy + "\n" + fear + "\n" + disgust + "\n" + anger;
  }
}
