package com.lecor.lecor.testrssdigischool.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String ITEM_KEY="id";
    private static final String ITEM_LINK="link";
    private static final String ITEM_TITLE="title";
    private static final String ITEM_DESCRIPTION="description";
    private static final String ITEM_PUBDATE="pubDate";
    private static final String ITEM_IMAGELINK="imageLink";
    private static final String ITEM_IMAGESTORAGE="imageStorage";

    private static final String ITEM_TABLE_NAME="Item";
    private static final String ITEM_TABLE_CREATE=
            "CREATE TABLE "+ITEM_TABLE_NAME+" ("+
            ITEM_KEY+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ITEM_LINK+" TEXT, "+
            ITEM_TITLE+" TEXT, "+
            ITEM_DESCRIPTION+" TEXT, "+
            ITEM_PUBDATE+" TEXT, "+
            ITEM_IMAGELINK+" TEXT, "+
            ITEM_IMAGESTORAGE+" TEXT);";

    private static final String ITEM_TABLE_DROP="DROP TABLE IF EXIST "+ITEM_TABLE_NAME+" ;";

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(ITEM_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ITEM_TABLE_DROP);
        onCreate(db);
    }
}
