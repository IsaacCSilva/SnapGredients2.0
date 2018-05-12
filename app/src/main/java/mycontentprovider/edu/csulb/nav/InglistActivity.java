package mycontentprovider.edu.csulb.nav;


import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InglistActivity extends SQLiteOpenHelper {

    // Variables for the app
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spinnerExample";
    private static final String TABLE_LABELS = "labels";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private SQLiteDatabase db;

    /**
     * The class for the ingredients in terms of the main activity for the app
     * @param context
     */
    public InglistActivity(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    /**
     * On create, make a table for the database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LABELS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    /**
     * Upgrades the database
     * @param db    The database to be used
     * @param oldVersion    Old database
     * @param newVersion    New database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        onCreate(db);
    }

    /**
     * Inserts a label for the food
     * @param label The ingredient for the food
     */
    public void insertLabel(String label){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,label);
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }

    /**
     * Gets all the labels from the app
     * @return  All the labels in the ingredients
     */
    public List<String>getAllLabels(){
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_LABELS;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(1));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }


    /**
     * Do this when the button clicked
     * @param n String that lets you choose what to delete
     */
    public void delete (String n){
        db = this.getReadableDatabase();
        db.delete(TABLE_LABELS, n, null);
        db.close();

    }
}




