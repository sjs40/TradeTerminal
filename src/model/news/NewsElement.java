package model.news;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.ibm.Emotion;
import model.ibm.Sentiment;
import util.JsonReader;

public class NewsElement {

  private String datetime;
  private String headline;
  private String source;
  private String url;
  private String summary;
  private String related;
  private String ticker;
  private NaturalLanguageUnderstanding service;

  public NewsElement(JSONObject json, String ticker) {
    datetime = json.getString("datetime");
    headline = json.getString("headline");
    source = json.getString("source");
    url = json.getString("url");
    summary = json.getString("summary");
    related = json.getString("related");
    this.ticker = ticker;
    service = new NaturalLanguageUnderstanding(
            NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
            "03db16ae-a602-4c2a-a844-3b789c6baf39",
            "pnuHAmxfpbKU"
    );
  }

  public Sentiment getSentiment() {
    String text = headline + "\n" + summary;

    SentimentOptions sentiment = new SentimentOptions.Builder()
            .build();

    Features features = new Features.Builder()
            .sentiment(sentiment)
            .build();

    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .text(text)
            .features(features)
            .build();

    AnalysisResults results = service
                    .analyze(parameters)
                    .execute();

    service = null;

    return new Sentiment(results);
  }

  public Emotion getEmotion() {
    String text = headline + "\n" + summary;

    EmotionOptions emotion = new EmotionOptions.Builder()
            .build();

    Features features = new Features.Builder()
            .emotion(emotion)
            .build();

    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .text(text)
            .features(features)
            .build();

    AnalysisResults results = service.analyze(parameters).execute();

    return new Emotion(results);
  }

  public String toString() {
    return headline + "\n" +
            source + "\n" +
            "URL: " + url + "\n" +
            "Summary: " + summary + "\n" +
            "Related: " + related + "\n" +
            getSentiment().toString() + "\n";
  }

  public String toStringWithEmotion() {
    return headline + "\n" +
            source + "\n" +
            "URL: " + url + "\n" +
            "Summary: " + summary + "\n" +
            "Related: " + related + "\n" +
            getEmotion().toString() + "\n";
  }

}
