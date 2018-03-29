package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.List;

import dependency.greendao.test.tinder.directional.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static List<Profile> loadProfiles(DatabaseHelper db) {
        List<dependency.greendao.test.tinder.directional.Profile> profiles = new ArrayList<>();
        Cursor cursor = db.getAll();
        Profile temp = new Profile("test");

        int count = cursor.getColumnCount();
        String name;
        String tweet;
        String tweetID;
        String imageURL;

        if (cursor!=null )
        {

            if  (cursor.moveToFirst())
            {
                do
                {
                    for (int i = 0 ; i< count; i++)
                    {
                        if (i == 1) {
                            temp.setName(cursor.getString(1));
                        }

                        else if (i == 2){
                            temp.setImage(cursor.getString(2));
                        }
                        else if (i == 3){
                            temp.setTweet(cursor.getString(3));
                        }

                        else if (i == 4){
                            temp.setTweetID(cursor.getString(4));
                        }
                    }
                    profiles.add(temp);
                }
                while (cursor.moveToNext());
            }

        }
            cursor.close();
            return profiles;
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    return null;
        //}
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
