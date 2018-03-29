package dependency.greendao.test.tinder.directional;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Swiper";
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

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Users " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " INTEGER)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE Tweets " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INTEGER, " + COL7 + " INTEGER " + COL9 + " TEXT " + COL10 + " TEXT)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE Topics " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL8 + " TEXT, " + COL3 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM Tweets";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public Cursor getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Tweets";
        Cursor data = db.rawQuery(query, null);
        return data;
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
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", st.getUser().getName());
        contentValues.put("tweet", st.getText());
        contentValues.put("tweetID", st.getId());
        contentValues.put("imageURL", st.getUser().getProfileImageURL());
        contentValues.put("username", st.getUser().getScreenName());
        contentValues.put("location", st.getUser().getLocation());
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

    public Cursor getData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean setView(String id, Integer i){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Tweets set viewed = " + i + " WHERE tweetID = " + id;
        db.execSQL(query);
        return true;
    }

    public boolean findData (String field, String value, String table) {
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

    public Cursor getItemID(String table, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + table +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
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
























