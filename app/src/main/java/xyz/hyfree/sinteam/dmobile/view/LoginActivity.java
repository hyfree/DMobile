package xyz.hyfree.sinteam.dmobile.view;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.Dao.LoginDao;
import xyz.hyfree.sinteam.dmobile.R;

/**
 * 登录界面
 *
 * 修订 2018-9-11
 *
 */
public class LoginActivity extends Activity {
    private ImageView iv_return;
    private TextView tv_register;
    private Button button_login;
    private EditText editText_name,editText_pass;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_name= (EditText) findViewById(R.id.email);
        editText_pass= (EditText) findViewById(R.id.password);
        button_login= (Button) findViewById(R.id.btn_login_login);

        //点击登录
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id=editText_name.getText().toString();
                String user_pass=editText_pass.getText().toString();

                LoginDao loginDao=new LoginDao(LoginActivity.this);

                String success=loginDao.userQuery(user_id,user_pass);
                if(!("".equals(success))){
                    SharedPreferences sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", success);
                    editor.putString("userid",user_id);
                    editor.commit();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //点击跳回“我的”界面
        iv_return= (ImageView) findViewById(R.id.iv_arrow_left);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(LoginActivity.this,MyselfActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

        //点击跳到注册界面
        tv_register= (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
