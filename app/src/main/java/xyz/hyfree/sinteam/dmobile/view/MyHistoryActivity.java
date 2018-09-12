package xyz.hyfree.sinteam.dmobile.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.Dao.HistoryDao;
import xyz.hyfree.sinteam.dmobile.R;
import xyz.hyfree.sinteam.dmobile.adapter.HistoryAdapter;
import xyz.hyfree.sinteam.dmobile.entity.History;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的足迹
 *
 * 修订 2018-9-11
 *
 */
public class MyHistoryActivity extends Activity {
    private List<History> list;
    private SharedPreferences sharedPreferences;
    private ListView lv_his;
    private TextView charge;
    private ImageView tv_arrow;
    CheckBox c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhistory);

        init();

        show();



    }



    public void show(){

        HistoryDao historyDao=new HistoryDao(MyHistoryActivity.this);
        //建立数据源
        list=new ArrayList<>();
        String uid =sharedPreferences.getString("userid","");
        list=historyDao.queryHistory(uid);
        for(History h:list) {
            Log.i("tag",h.getG_name());
        }

        //建立适配器
        HistoryAdapter historyAdapter=new HistoryAdapter(list,MyHistoryActivity.this);
        //加载适配器
        lv_his.setAdapter(historyAdapter);

        /*lv_his.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyHistoryActivity.this, list.get(position).getG_name(), Toast.LENGTH_SHORT).show();
            }
        });*/
        lv_his.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyHistoryActivity.this, "管理", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void init(){
        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        lv_his= (ListView) findViewById(R.id.lv_his);
        tv_arrow= (ImageView) findViewById(R.id.iv_arrow_left);
        charge= (TextView) findViewById(R.id.tv_guanli);
        tv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
