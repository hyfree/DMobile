package xyz.hyfree.sinteam.dmobile.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.Dao.GoodsDao;
import xyz.hyfree.sinteam.dmobile.Dao.HistoryDao;
import xyz.hyfree.sinteam.dmobile.adapter.GoodsListAdapter;
import xyz.hyfree.sinteam.dmobile.R;
import xyz.hyfree.sinteam.dmobile.entity.Goods;

import java.util.List;

public class GoodslistActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView arrow_left,serach;
    private Intent intent;
    private List<Goods> list;
    private GridView gridView;
    private SharedPreferences sharedPreferences,sharedPreferences2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);

        sharedPreferences=getSharedPreferences("goods", Context.MODE_PRIVATE);
        sharedPreferences2=getSharedPreferences("name", Context.MODE_PRIVATE);
        gridView= (GridView) findViewById(R.id.gv_food);
        intent=getIntent();
        int position=intent.getIntExtra("position",10);//获取intent的携带数据
        if (position==0){
            GoodsDao goodsDao=new GoodsDao(GoodslistActivity.this);
            //数据源
            list= goodsDao.foodQuery();
            //适配器
            GoodsListAdapter goodsListAdapter=new GoodsListAdapter(GoodslistActivity.this,list);
            //加载适配器
            gridView.setAdapter(goodsListAdapter);


            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Goods goods=new Goods();
                    goods=list.get(position);


                    //将信息添加到足迹表
                    String uid=sharedPreferences2.getString("userid","");
                    String gname=goods.getName();
                    int gpic=goods.getPic();
                    String gprice=goods.getPrice();
                    HistoryDao historyDao=new HistoryDao(GoodslistActivity.this);
                    historyDao.insertHistory(uid,gname,gpic,gprice);

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putInt("pic",goods.getPic());
                    editor.putString("name",goods.getName());
                    editor.putString("price",goods.getPrice());
                    editor.putString("date",goods.getDate());
                    editor.putString("baozhiqi",goods.getQualityperiod());
                    editor.commit();


                    //到商品详情页面
                    Intent intent=new Intent(GoodslistActivity.this,GoodsdetailActivity.class);
                    startActivity(intent);
                }
            });

        }else if(position==1){
            Toast.makeText(GoodslistActivity.this, position+"", Toast.LENGTH_SHORT).show();

        }else if(position==2){
            Toast.makeText(GoodslistActivity.this, position+"", Toast.LENGTH_SHORT).show();

        }else if(position==3){
            Toast.makeText(GoodslistActivity.this, position+"", Toast.LENGTH_SHORT).show();

        }else if(position==4){
            Toast.makeText(GoodslistActivity.this, position+"", Toast.LENGTH_SHORT).show();

        }else if(position==5){
            Toast.makeText(GoodslistActivity.this, position+"", Toast.LENGTH_SHORT).show();
        }else if(position==10){
            Log.e("position","出现错误");
        }



        //点击返回MainActivity
        arrow_left= (ImageView) findViewById(R.id.iv_arrow_lift);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodslistActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
