package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static List<Profile> loadProfiles(Context context) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));
            List<Profile> profileList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Profile profile = gson.fromJson(array.getString(i), Profile.class);
                profileList.add(profile);
            }
            return profileList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Status> loadStatus() {
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("20yNRIOurzQaKzs9t7C4HXWuV")
                .setOAuthConsumerSecret("Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD")
                .setOAuthAccessToken("1942242924-aQgcIo4phlvOu38IGOphtIazT3mSiWUAnkTypHX")
                .setOAuthAccessTokenSecret("zTY5tAJKVG6aGW44DIEHzprxBDXZzpxVQfA44dcjl6KSt");

        try {
            TwitterFactory tf = new TwitterFactory(cf.build());

            twitter4j.Twitter twitter = tf.getInstance();

            List<Status> status = twitter.getHomeTimeline();
            for (Status st : status) {
                System.out.println(st.getUser().getName() + "\n" + st.getText());
                System.out.println("Retweet Count - " + st.getRetweetCount());
                System.out.println("Date - " + st.getCreatedAt());
                System.out.println("Location - " + st.getGeoLocation());
                System.out.println("Favorites - " + st.getFavoriteCount() + "\n");
            }
            return status;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    };


    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is = null;
        try {
            AssetManager manager = context.getAssets();
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Point getDisplaySize(WindowManager windowManager) {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                return new Point(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Point(0, 0);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
