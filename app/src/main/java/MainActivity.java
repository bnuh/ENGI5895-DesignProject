package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;

import java.util.List;

import twitter4j.Status;

public class MainActivity extends AppCompatActivity implements Card.Callback {

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private List<Status> statuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        // Different size for horizontal

        for (Profile profile : Utils.loadProfiles(this.getApplicationContext())) {
            mSwipeView.addView(new Card(mContext, profile, cardViewHolderSize, this));
        }


    }

    public void onSwipeUp() {
        // Favorited
    }
}
