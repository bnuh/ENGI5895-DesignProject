package dependency.greendao.test.tinder.directional;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Date;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "Swiper";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "rating";
    private static final String COL4 = "imageURL";
    private static final String COL5 = "tweet";
    private static final String COL6 = "tweetID";
    private static final String COL7 = "viewed";
    private static final String COL8 = "topic";
    private static final String COL9 = "username";
    private static final String COL10 = "location";
    private static final String COL11 = "date";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE IF NOT EXISTS Users " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " REAL)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE IF NOT EXISTS Tweets " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INTEGER, " + COL7 + " INTEGER, " + COL9 + " TEXT, " + COL10 + " TEXT, " +  COL11 + " TEXT)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE IF NOT EXISTS Topics " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL8 + " TEXT, " + COL3 + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS Users " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " REAL)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE IF NOT EXISTS Tweets " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INTEGER, " + COL7 + " INTEGER, " + COL9 + " TEXT, " + COL10 + " TEXT, " +  COL11 + " TEXT)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE IF NOT EXISTS Topics " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL8 + " TEXT, " + COL3 + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + DB_NAME);
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
            temp.setID(data.getString(data.getColumnIndex("tweetID")));
            temp.setTweet(data.getString(data.getColumnIndex("tweet")));
            temp.setImage(data.getString(data.getColumnIndex("imageURL")));
            temp.setUsername(data.getString(data.getColumnIndex("username")));
            temp.setLocation(data.getString(data.getColumnIndex("location")));
            temp.setDate(data.getString(data.getColumnIndex("date")));
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

    public void addCol(String tablename, String colName, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String createCol = "ALTER TABLE " + tablename + " ADD COLUMN " + colName + type;
        db.execSQL(createCol);
    }

    public boolean addData(String table, String item, String col) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col, item);

        Log.d(TAG, "addData: Adding " + item + " to " + table);

        long result = db.insert(table, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addTweet(twitter4j.Status st) {
        SQLiteDatabase db = this.getWritableDatabase();
        Date date = st.getCreatedAt();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", st.getUser().getName());
        if (st.isRetweet()){
            contentValues.put("tweet", st.getRetweetedStatus().getText());
            //contentValues.put("tweet", st.getText());
        }
        else {
            contentValues.put("tweet", st.getText());
        }
        contentValues.put("tweet", st.getText());
        contentValues.put("tweetID", st.getId());
        contentValues.put("imageURL", st.getUser().getBiggerProfileImageURL());
        contentValues.put("username", st.getUser().getScreenName());
        contentValues.put("location", st.getUser().getLocation());
        contentValues.put("date", String.format("%tm/%td/%tY", date, date, date));
        contentValues.put("viewed", 0);

        long result = db.insert("Tweets", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addUser(String name, String image) {
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

    public boolean reduceRating(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET rating = 0 WHERE name LIKE '%" + name + "%'";
        db.execSQL(query);
        return true;
    }

    public Cursor getData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void setView(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("viewed","1"); //These Fields should be your String values of actual column names
        db.update("Tweets", cv, "tweetID="+id, null);
    }

    public boolean findData (String field, String value, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM " + table + " WHERE " + field + " LIKE '%" + value + "%'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor getItemID(String table, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + table +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void setRating(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET rating = (rating/2) WHERE name = '" + id + "'";
        db.execSQL(query);
    }

    public void updateName(String table, String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + table + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateView(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE tweets SET viewed = 1 WHERE tweetID = '" + id + "'";
        Log.d(TAG, "up: query: " + query);
        Log.d(TAG, "updateView: Setting view");
        db.execSQL(query);
    }

    public void deleteName(String table, int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + table + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void createNew(String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE " + tablename + " (ID INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createTable);
    }


    public void deleteTable(String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "DROP TABLE [ IF EXISTS ] " + tablename;
        db.execSQL(createTable);
    }

}