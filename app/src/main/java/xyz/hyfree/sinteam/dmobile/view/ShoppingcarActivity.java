package xyz.hyfree.sinteam.dmobile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import xyz.hyfree.sinteam.dmobile.R;

/**
 *
 * 购物车
 *
 *
 *
 */
public class ShoppingcarActivity extends Activity {
    private String username="";
    private SharedPreferences sharedPreferences;
    private TextView textView_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcar);
        textView_name= (TextView) findViewById(R.id.tv_uname);

        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        username=sharedPreferences.getString("username","未登录");
        if("".equals(username)||"未登录".equals(username)){
            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingcarActivity.this);
            builder.setMessage("您还没有登录")
                    .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ShoppingcarActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        }else{
            textView_name.setText(username);
        }

    }





    public void doClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.iv_home:
                intent=new Intent(ShoppingcarActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_my:
                intent=new Intent(this,MyselfActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        username=sharedPreferences.getString("username","未登录");
        textView_name.setText(username);
    }
}
