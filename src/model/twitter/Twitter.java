package model.twitter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.PropertyConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Twitter {

  private twitter4j.Twitter twitter;

  public Twitter() {
    try {
      InputStream is = new FileInputStream("src/twitter.properties");
      Properties props = new Properties();
      props.load(is);
      PropertyConfiguration propConfig = new PropertyConfiguration(props);
      TwitterFactory factory = new TwitterFactory(propConfig);
      twitter = factory.getInstance();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getSearchResults(String ticker) {
    try {
      Query query = new Query("$" + ticker);
      QueryResult result = this.twitter.search(query);
      String resultStr = "";
      for (Status status : result.getTweets()) {
        String username = "@" + status.getUser().getScreenName();
        int favorites = status.getFavoriteCount();
        int retweets = status.getRetweetCount();
        resultStr += username + " Favs:" + favorites + " Retweets: " + retweets
                + "\n" + status.getText() + "\n\n";
      }
      return resultStr;
    } catch (TwitterException e) {
      e.printStackTrace();
    }
    return null;
  }
}
