package xyz.hyfree.sinteam.dmobile.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.R;

/**
 * 商品详情页面
 *
 *
 *
 */
public class GoodsdetailActivity extends AppCompatActivity {
    private int count=1;
    private ImageView iv_return;
    private SharedPreferences sharedPreferences;

    private TextView tv_name,tv_price,tv_date,tv_baozhiqi,tv_count;
    private ImageView iv_pic;
    private Button btn_jian,btn_jia,btn_buy,btn_addcar;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);


        sharedPreferences=getSharedPreferences("goods", Context.MODE_PRIVATE);


        //实例化
        tv_name= (TextView) findViewById(R.id.tv_detailname);
        tv_price= (TextView) findViewById(R.id.tv_detailprice);
        tv_date= (TextView) findViewById(R.id.tv_detailshangchanriqi);
        tv_baozhiqi= (TextView) findViewById(R.id.tv_detailbaozhiqi);
        tv_count= (TextView) findViewById(R.id.tv_detailnumber);
        iv_pic= (ImageView) findViewById(R.id.iv_detailpic);
        btn_jia= (Button) findViewById(R.id.btn_detailjia);
        btn_jian= (Button) findViewById(R.id.btn_detailjian);
        btn_addcar= (Button) findViewById(R.id.btn_addcar);
        btn_buy= (Button) findViewById(R.id.btn_buy);



        //加载详情信息
        tv_name.setText(sharedPreferences.getString("name",""));
        tv_price.setText(sharedPreferences.getString("price",""));
        tv_date.setText(sharedPreferences.getString("date",""));
        tv_baozhiqi.setText(sharedPreferences.getString("baozhiqi",""));
        iv_pic.setBackgroundResource(sharedPreferences.getInt("pic",R.drawable.arrow_left_icon));
        tv_count.setText(count+"");
        btn_jian.setBackgroundResource(R.color.hui);
        btn_jia.setBackgroundResource(R.color.bai);

        //增加商品数量
        btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_count.setText(count+"");
                if(count>1){
                    btn_jian.setBackgroundResource(R.color.bai);
                }
            }
        });

        //减少商品数量
        btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1){
                    count--;
                    tv_count.setText(count+"");
                }
                if (count==1){
                    btn_jian.setBackgroundResource(R.color.hui);
                }
            }
        });


        //加入购物车
        btn_addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
                String id=sharedPreferences.getString("userid","0");
                if("0".equals(id)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(GoodsdetailActivity.this);
                    builder.setMessage("您还没有登录")
                            .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(GoodsdetailActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
                }else{

                    Toast.makeText(GoodsdetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //现在购买
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
                String id=sharedPreferences.getString("userid","0");
                if("0".equals(id)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(GoodsdetailActivity.this);
                    builder.setMessage("您还没有登录")
                            .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(GoodsdetailActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
                }else{
                    Toast.makeText(GoodsdetailActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                }
            }
        });





        //退出商品详情界面
        iv_return= (ImageView) findViewById(R.id.iv_arrow_left);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
