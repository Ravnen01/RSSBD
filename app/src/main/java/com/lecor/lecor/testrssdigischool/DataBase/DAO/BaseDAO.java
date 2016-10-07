package com.lecor.lecor.testrssdigischool.DataBase.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lecor.lecor.testrssdigischool.DataBase.DataBaseHandler;




public abstract class BaseDAO {


    protected final static int VERSION = 1;

    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;

    protected DataBaseHandler mHandler = null;



    public BaseDAO(Context pContext) {
        this.mHandler = new DataBaseHandler(pContext, NOM, null, VERSION);
    }



    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }



    public void close() {
        mDb.close();
    }



    public SQLiteDatabase getDb() {
        return mDb;
    }
}
