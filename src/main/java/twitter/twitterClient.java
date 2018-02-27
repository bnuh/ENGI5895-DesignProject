package twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterClient {
     public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("20yNRIOurzQaKzs9t7C4HXWuV")
                .setOAuthConsumerSecret("Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD")
                .setOAuthAccessToken("1942242924-aQgcIo4phlvOu38IGOphtIazT3mSiWUAnkTypHX")
                .setOAuthAccessTokenSecret("zTY5tAJKVG6aGW44DIEHzprxBDXZzpxVQfA44dcjl6KSt");

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        List<Status> status = twitter.getHomeTimeline();
        for (Status st : status){
            System.out.println(st.getUser().getName()+ "\n" + st.getText());
            System.out.println("Retweet Count - " + st.getRetweetCount());
            System.out.println("Date - " + st.getCreatedAt());
            System.out.println("Location - " + st.getGeoLocation());
            System.out.println("Favorites - " + st.getFavoriteCount() + "\n");
        }
    }

}

