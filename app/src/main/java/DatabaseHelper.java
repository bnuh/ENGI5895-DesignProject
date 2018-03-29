package dependency.greendao.test.tinder.directional;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

import dependency.greendao.test.tinder.directional.Profile;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Swiper";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "rating";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Profile> getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Tweets";
        Cursor data = db.rawQuery(query, null);
        ArrayList<Profile> array = new ArrayList<Profile>();
        while (data.moveToNext()) {
            Profile temp = new Profile();
            temp.setName(data.getString(data.getColumnIndex("name")));
            temp.setTweetID(data.getString(data.getColumnIndex("tweetID")));
            temp.setTweet(data.getString(data.getColumnIndex("tweet")));
            temp.setImage(data.getString(data.getColumnIndex("imageURL")));
            array.add(temp);
            data.moveToNext();
        }
        return array;
    }

    public void createNew(SQLiteDatabase db, String tablename) {
        String createTable = "CREATE TABLE " + tablename + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " INTEGER)";
        db.execSQL(createTable);
    }

    public void addCol(SQLiteDatabase db, String tablename, String colName, String type) {
        String createCol = "ALTER TABLE " + tablename + " ADD COLUMN " + colName + type;
        db.execSQL(createCol);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, 1);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addTweet(twitter4j.Status st) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", st.getUser().getName());
        contentValues.put("tweet", st.getText());
        contentValues.put("tweetID", st.getId());
        contentValues.put("imageURL", st.getUser().getProfileImageURL());
        //contentValues.put("username", st.getUser().getScreenName());
        //contentValues.put("location", st.getUser().getLocation());
        contentValues.put("viewed", 0);

        long result = db.insert("Tweets", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("rating", 1);

        Log.d("DB", "addData: Adding " + name + " to " + "Users");

        long result = db.insert("Users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean findData (String field, long value, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM " + table + " WHERE " + field + " = '" + value + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void reduceUserRating(String tablename, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET rating = (rating/2) WHERE name = " + id;
        db.execSQL(query);
    }

    public void restoreUserRating(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET rating = " + 1 + " WHERE name = " + id;
        db.execSQL(query);
    }

    public void reduceTopicRating(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Topics SET rating = (rating/2) WHERE topic = " + topic;
        db.execSQL(query);
    }

    public void restoreTopicRating(String tablename, String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Topics set rating = " + 1 + " WHERE topic = " + topic;
        db.execSQL(query);
    }

}
























