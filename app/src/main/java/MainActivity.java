package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.util.Log;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import android.os.StrictMode;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity implements Card.Callback {

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private List<Status> statuses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        TwitterManager t = new TwitterManager();

        mSwipeView = findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeAnimTime(mAnimationDuration)
                        .setRelativeScale(0.01f)
                        );


        Point cardViewHolderSize = new Point(windowSize.x, windowSize.y - bottomMargin);

        for (Profile profile : Utils.loadProfiles(this.getApplicationContext())) {
            mSwipeView.addView(new Card(mContext, profile, cardViewHolderSize, this));
        }

        Log.d("DEMO", "Main Activity Done");
//        t.refreshTwitter();

    }

    public void onSwipeUp() {
        // Favorited
    }

    public void refreshTwitter() {
        List<Status> homeTimeline = null;

        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("20yNRIOurzQaKzs9t7C4HXWuV")
                .setOAuthConsumerSecret("Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD")
                .setOAuthAccessToken("1942242924-aQgcIo4phlvOu38IGOphtIazT3mSiWUAnkTypHX")
                .setOAuthAccessTokenSecret("zTY5tAJKVG6aGW44DIEHzprxBDXZzpxVQfA44dcjl6KSt");

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twit = tf.getInstance();

        try {
            homeTimeline = twit.getHomeTimeline();
        }
        catch (Exception te) { Log.e("error", "Exception: " + te); }

        for (Status statusUpdate : homeTimeline)
        {
            Log.d("demo", "demo");
        }
    }

    public class TwitterManager {

        public void refreshTwitter() {
            List<Status> homeTimeline = null;

            ConfigurationBuilder cf = new ConfigurationBuilder();

            cf.setDebugEnabled(true)
                    .setOAuthConsumerKey("20yNRIOurzQaKzs9t7C4HXWuV")
                    .setOAuthConsumerSecret("Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD")
                    .setOAuthAccessToken("1942242924-aQgcIo4phlvOu38IGOphtIazT3mSiWUAnkTypHX")
                    .setOAuthAccessTokenSecret("zTY5tAJKVG6aGW44DIEHzprxBDXZzpxVQfA44dcjl6KSt");

            TwitterFactory tf = new TwitterFactory(cf.build());
            twitter4j.Twitter twit = tf.getInstance();

            try {
                homeTimeline = twit.getHomeTimeline();
            }
            catch (Exception te) { Log.e("demo", "Exception: " + te); }

            for (Status statusUpdate : homeTimeline)
            {
                Log.d("demo", "Test");
            }
        }


    }

}
