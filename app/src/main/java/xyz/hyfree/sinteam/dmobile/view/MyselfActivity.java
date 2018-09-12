package xyz.hyfree.sinteam.dmobile.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import xyz.hyfree.sinteam.dmobile.R;

/**
 *
 */
public class MyselfActivity extends Activity {
    private LinearLayout linearLayout;
    private Button btn_login,btn_info,btn_order,btn_history;
    private Intent intent;
    private TextView tv_loginState;
    private String userName="";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        linearLayout= (LinearLayout) findViewById(R.id.ll);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_info= (Button) findViewById(R.id.btn_info);
        btn_history= (Button) findViewById(R.id.btn_history);
        btn_order= (Button) findViewById(R.id.btn_order);

        //登录状态
        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        userName=sharedPreferences.getString("username","未登录");
        tv_loginState= (TextView) findViewById(R.id.tv_login_state);
        tv_loginState.setText(userName);
        if("未登录".equals(tv_loginState.getText().toString())){
            linearLayout.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            btn_login.setText("退出");
        }


        //点击登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=btn_login.getText().toString();
                if("登录".equals(str)){
                    Intent intent=new Intent(MyselfActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    quit();
                }

            }
        });


        //点击查询我的足迹
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyselfActivity.this,MyHistoryActivity.class);
                startActivity(intent);
            }
        });

        //点击查询我的基本信息
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyselfActivity.this,MyinfoActivity.class);
                startActivity(intent);
            }
        });
    }


    //退出
    public void quit(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", "未登录");
        editor.putString("userid","0");
        editor.commit();
        btn_login.setText("登录");
        linearLayout.setVisibility(View.GONE);
        userName=sharedPreferences.getString("username","未登录");

        tv_loginState.setText(userName);
    }

    //界面跳转
    public void doClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.iv_home:
                intent=new Intent(MyselfActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_shoppingcar:
                intent=new Intent(MyselfActivity.this,ShoppingcarActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        userName=sharedPreferences.getString("username","未登录");
        tv_loginState.setText(userName);
        if("未登录".equals(tv_loginState.getText().toString())){
            linearLayout.setVisibility(View.GONE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            btn_login.setText("退出");
        }
    }
}
