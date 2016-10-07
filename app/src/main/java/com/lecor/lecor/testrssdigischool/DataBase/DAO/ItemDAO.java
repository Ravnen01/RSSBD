package com.lecor.lecor.testrssdigischool.DataBase.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lecor.lecor.testrssdigischool.Model.Item;

import java.util.ArrayList;



public class ItemDAO extends BaseDAO {
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
    private static final String ITEM_TABLE_TRUNCATE="TRUNCATE TABLE "+ITEM_TABLE_NAME+" ;";

    public ItemDAO(Context pContext) {
        super(pContext);


    }

    public void ajouterItem(Item item){
        open();
        ContentValues values=new ContentValues();
        values.put(ITEM_LINK,item.getLink());
        values.put(ITEM_TITLE,item.getTitle());
        values.put(ITEM_DESCRIPTION,item.getDescription());
        values.put(ITEM_PUBDATE,item.getPubDate());
        values.put(ITEM_IMAGELINK,item.getImageLink());
        values.put(ITEM_IMAGESTORAGE,item.getImageStorage());
        mDb.insert(ITEM_TABLE_NAME,null,values);
        close();

    }

    public void modifierItem(Item item){
        ContentValues values=new ContentValues();
        values.put(ITEM_KEY,item.getId());
        values.put(ITEM_LINK,item.getLink());
        values.put(ITEM_TITLE,item.getTitle());
        values.put(ITEM_DESCRIPTION,item.getDescription());
        values.put(ITEM_PUBDATE,item.getPubDate());
        values.put(ITEM_IMAGELINK,item.getImageLink());
        values.put(ITEM_IMAGESTORAGE,item.getImageStorage());
        mDb.update(ITEM_TABLE_NAME, values, ITEM_KEY  + " = ?", new String[] {String.valueOf(item.getId())});
    }

    public void suprimmerItem(int id){
        mDb.delete(ITEM_TABLE_NAME, ITEM_KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void viderTable(){
        open();
        mDb.delete(ITEM_TABLE_NAME,null,null);
        close();
    }

    public ArrayList<Item> getListitem(){
        open();
        ArrayList<Item> listItem=new ArrayList<>();
        Cursor cursor=mDb.rawQuery("SELECT * FROM "+ITEM_TABLE_NAME+";",null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String link=cursor.getString(1);
            String title=cursor.getString(2);
            String desciption=cursor.getString(3);
            String pubDate=cursor.getString(4);
            String imageLink=cursor.getString(5);
            String imageStorage=cursor.getString(6);
            Item item=new Item(id,link,title,desciption,pubDate,imageLink,imageStorage);
            listItem.add(item);
        }

        cursor.close();
        close();
        return listItem;
    }

}
