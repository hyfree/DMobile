package xyz.hyfree.sinteam.dmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_search;
    private ImageView iv_search,iv_homePage,iv_weather,iv_myself,iv_shoppingCar;
    private GridView gridView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> list;//数据源
    int[] pic = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6};//图片数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView= (GridView) findViewById(R.id.gv);
        iv_homePage= (ImageView) findViewById(R.id.iv_home);
        iv_shoppingCar= (ImageView) findViewById(R.id.iv_shoppingcar);
        iv_myself= (ImageView) findViewById(R.id.iv_my);
        
        //建立数据源
        list=new ArrayList<Map<String,Object>>();
        for(int i=0;i<6;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("pic",pic[i]);
           /* map.put("pic2",pic[i+1]);*/
            list.add(map);
        }
        //建立适配器
        SimpleAdapter simpleAdapter=new SimpleAdapter(MainActivity.this,list,R.layout.main_items,new String[]{"pic",},new int[]{R.id.iv_first});
        gridView.setAdapter(simpleAdapter);

        //各区跳转
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "进入食品区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);//携带数据
                    startActivity(intent);
                } else if (position == 1) {
                    Toast.makeText(MainActivity.this, "进入妇婴区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else if (position == 2) {
                    Toast.makeText(MainActivity.this, "进入护肤品区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else if (position == 3) {
                    Toast.makeText(MainActivity.this, "进入纺织区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else if (position == 4) {
                    Toast.makeText(MainActivity.this, "进入零食区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else if (position == 5) {
                    Toast.makeText(MainActivity.this, "进入日用品区", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, GoodslistActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            }
        });







        //点击“我的”进入我的页面
        iv_myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyselfActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //点击购物车进入购物车界面
        iv_shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShoppingcarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
