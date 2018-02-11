import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SentimentTest {
  public static void main(String[] args) {
    NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
            NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
            "03db16ae-a602-4c2a-a844-3b789c6baf39",
            "pnuHAmxfpbKU"
    );

    String url = "https://www.thestreet.com/story/14479233/1/cisco-stock-a-buy-with-17-percent-upside.html";

    List<String> targets = new ArrayList<>();
    targets.add("CSCO");

    SentimentOptions sentiment = new SentimentOptions.Builder()
            .targets(targets)
            .build();

    Features features = new Features.Builder()
            .sentiment(sentiment)
            .build();

    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .url(url)
            .features(features)
            .build();

    AnalysisResults response = service.analyze(parameters).execute();

    System.out.println(response);

    JSONObject json = new JSONObject(response);
    JSONObject sentimentJson = json.getJSONObject("sentiment");
    JSONObject docJson = sentimentJson.getJSONObject("document");
    System.out.println(docJson.getDouble("score"));
  }
}
