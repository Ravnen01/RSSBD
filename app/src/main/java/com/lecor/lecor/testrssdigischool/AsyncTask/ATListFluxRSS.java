package com.lecor.lecor.testrssdigischool.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Adapter;

import com.lecor.lecor.testrssdigischool.DataBase.DAO.BaseDAO;
import com.lecor.lecor.testrssdigischool.DataBase.DAO.ItemDAO;
import com.lecor.lecor.testrssdigischool.Interface.ActivityListView;
import com.lecor.lecor.testrssdigischool.Model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ATListFluxRSS extends AsyncTask {

    private Context context;

    private ActivityListView activityListView;
    ArrayList<Item> listItem;

    public ATListFluxRSS(Context context, ActivityListView activityListView) {
        this.context = context;

        this.activityListView = activityListView;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URL url=new URL("http://www.lemonde.fr/m-actu/rss_full.xml");

            URLConnection urlConnection=url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();
            parser.setInput(reader);
            listItem=parseXml(parser);
            new ItemDAO(context).viderTable();
            String file_path =Environment.getExternalStorageDirectory() + File.separator + "TestDigischool"+File.separator+"images";
            File dir = new File(file_path);
            if(!dir.exists()) {
                dir.mkdirs();
            }else{
                dir.delete();
                dir.mkdirs();
            }

            for(int i=0;i<listItem.size();i++){
                URL ulrn = new URL(listItem.get(i).getImageLink());
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                InputStream is = con.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(is);

                File file = new File(dir, i+".jpg");
                FileOutputStream fOut = new FileOutputStream(file);

                bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                fOut.flush();
                fOut.close();

                listItem.get(i).setImageStorage(i+".jpg");

                new ItemDAO(context).ajouterItem(listItem.get(i));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        activityListView.addItem();
    }

    private ArrayList<Item> parseXml(XmlPullParser parser) throws XmlPullParserException, IOException {
        boolean insideItem = false;
        Item item=new Item();
        ArrayList<Item> listItem=new ArrayList<>();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {

                switch (parser.getName()) {
                    case "item":
                        insideItem = true;
                        item=new Item();
                        break;

                    case "link":
                        if(insideItem)
                            item.setLink(parser.nextText());
                        break;
                    case "title":
                        if(insideItem)
                            item.setTitle(parser.nextText());
                            break;
                    case "description":
                        if(insideItem)
                            item.setDescription(parser.nextText());
                            break;
                    case "pubDate":
                        if(insideItem)
                            item.setPubDate(parser.nextText());
                            break;
                    case "enclosure":
                        if(insideItem)
                            item.setImageLink(parser.getAttributeValue(null,"url"));
                            break;
                }


            } else if (eventType == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("item")) {
                insideItem = false;
                listItem.add(item);
            }

            eventType = parser.next(); // move to next element
        }

        return listItem;
    }
}

