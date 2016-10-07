package com.lecor.lecor.testrssdigischool;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lecor.lecor.testrssdigischool.Adapter.AdapterListMainActivity;
import com.lecor.lecor.testrssdigischool.AsyncTask.ATListFluxRSS;
import com.lecor.lecor.testrssdigischool.DataBase.DAO.ItemDAO;
import com.lecor.lecor.testrssdigischool.Interface.ActivityListView;
import com.lecor.lecor.testrssdigischool.Model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityListView{

    private ListView lvMainactivity;
    private AdapterListMainActivity adapter;
    ArrayList<Item> listItem=new ArrayList<>();
    private SwipeRefreshLayout swipeContainer;
    private Context context;
    private ActivityListView activityListView=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        lvMainactivity=(ListView)findViewById(R.id.lvMainActivity);
        listItem=new ItemDAO(getApplicationContext()).getListitem();
        adapter=new AdapterListMainActivity(listItem,getApplicationContext());
        swipeContainer=(SwipeRefreshLayout)findViewById(R.id.activity_main);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(Connectivity.isConnected(context)) {
                    new ATListFluxRSS(context, activityListView).execute();
                }else{
                    Toast.makeText(context,"Connexion Impossible",Toast.LENGTH_SHORT).show();
                    swipeContainer.setRefreshing(false);
                }
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvMainactivity.setAdapter(adapter);
        if(Connectivity.isConnected(context)) {
            new ATListFluxRSS(context, activityListView).execute();
        }else{
            Toast.makeText(context,"Connexion Impossible",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addItem() {
        this.listItem=new ItemDAO(getApplicationContext()).getListitem() ;
        adapter.dataChange(listItem);
        swipeContainer.setRefreshing(false);
    }
}
