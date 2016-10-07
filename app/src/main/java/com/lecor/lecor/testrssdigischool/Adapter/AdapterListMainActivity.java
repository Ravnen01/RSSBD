package com.lecor.lecor.testrssdigischool.Adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.net.Uri;

import com.lecor.lecor.testrssdigischool.Model.Item;
import com.lecor.lecor.testrssdigischool.R;


import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class AdapterListMainActivity extends BaseAdapter{

    private ArrayList<Item> listItem;
    private Context context;



    public AdapterListMainActivity(ArrayList<Item> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        Item item=listItem.get(position);

        //if it the first creation
        if(convertView == null) {
            layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.adapter_fluxlistview, null);
        } else {
            // reuse an existing view so we need to re-set all parameters
            layout = (LinearLayout)convertView;
        }

        TextView tvItem=(TextView)layout.findViewById(R.id.tvFluxListAdapter);
        ImageView imageView=(ImageView)layout.findViewById(R.id.ivFluxListAdapter);

        tvItem.setText(item.getTitle());

        File myFile = new File(Environment.getExternalStorageDirectory() +
                File.separator + "TestDigischool"+File.separator+"images",item.getImageStorage());
        imageView.setImageURI(Uri.parse(myFile.getAbsolutePath()));

        return layout;
    }

    public void dataChange(ArrayList<Item> listItem){
        this.listItem=listItem;
        notifyDataSetChanged();
    }
}
