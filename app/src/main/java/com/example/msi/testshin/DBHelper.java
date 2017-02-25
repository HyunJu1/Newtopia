package com.example.msi.testshin;

/**
 * Created by msi on 2017-02-25.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NewsBoard.db";
    public static final String MOVIES_TABLE_NAME = "boards";
    public static final String MOVIES_COLUMN_ID = "id";
    public static final String MOVIES_COLUMN_NAME = "name";
    public static final String MOVIES_COLUMN_NEWSURL = "newsUrl";
    public static final String MOVIES_COLUMN_OPTION = "option";
    public static final String MOVIES_COLUMN_SUBJECT = "subject";
    public static final String MOVIES_COLUMN_FIELD = "field";
    public static final String MOVIES_COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table boards " +
                        "(id integer primary key,name text, newsUrl text, option text, subject text, field text, date Date)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS boards");
        onCreate(db);
    }

    public boolean insertBoard(String newsUrl, String option, String subject, String field,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("newsUrl", newsUrl);
        contentValues.put("option", option);
        contentValues.put("subject", subject);
        contentValues.put("field", field);
        contentValues.put("date", Integer.toString(Calendar.DATE));
        db.insert("boards", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from boards where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MOVIES_TABLE_NAME);
        return numRows;
    }

    public boolean updateBoard(String newsUrl, String option, String subject, String field, Integer id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("newsUrl", newsUrl);
        contentValues.put("name", name);
        contentValues.put("option", option);
        contentValues.put("subject", subject);
        contentValues.put("field", field);
        contentValues.put("id",id );
        contentValues.put("date", Integer.toString(Calendar.DATE));
        db.update("boards", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteBoard(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("boards",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllBoards() {
        ArrayList<HashMap<String,String>> array_list = new ArrayList<HashMap<String,String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from boards", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            HashMap<String,String> h = new HashMap<String,String>();
            h.put("option",res.getString(res.getColumnIndex(MOVIES_COLUMN_OPTION)));
            h.put("subject",res.getString(res.getColumnIndex(MOVIES_COLUMN_SUBJECT)));
            h.put("name",res.getString(res.getColumnIndex(MOVIES_COLUMN_NAME)));
            h.put("date",res.getString(res.getColumnIndex(MOVIES_COLUMN_DATE)));
            array_list.add(h);
            res.moveToNext();
        }
        return array_list;
    }
}
