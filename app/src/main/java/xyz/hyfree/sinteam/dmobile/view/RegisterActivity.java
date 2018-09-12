package xyz.hyfree.sinteam.dmobile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.Dao.RegisterDao;
import xyz.hyfree.sinteam.dmobile.R;

/**
 * 注册处理
 *
 * 修订 2018-9-11
 *
 */
public class RegisterActivity extends Activity {
    private ImageView iv_return;
    private Button btn_submit;
    private EditText et_name,et_pass,et_pass2,et_age,et_phone,et_adress;

    private String sex="";

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        //选择性别

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbtn_man){
                    //Toast.makeText(RegisterActivity.this, "nan", Toast.LENGTH_SHORT).show();
                    sex="男";
                }else {
                    //Toast.makeText(RegisterActivity.this, "nv", Toast.LENGTH_SHORT).show();
                    sex="女";
                }
            }
        });

        //点击跳到登录界面
        iv_return= (ImageView) findViewById(R.id.iv_arrow_left);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //点击提交注册表信息给dao层
        btn_submit= (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDao registerDao=new RegisterDao(RegisterActivity.this);
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                String pass2=et_pass2.getText().toString();
                String age=et_age.getText().toString();
                String phone=et_phone.getText().toString();
                String adress=et_adress.getText().toString();
                if ("".equals(name)||"".equals(pass)||"".equals(pass2)||"".equals(age)||"".equals(phone)||"".equals(adress)||"".equals(sex)){
                    Toast.makeText(RegisterActivity.this, "必须全部填写", Toast.LENGTH_SHORT).show();
                }
                else if(!(pass.equals(pass2))){
                    Toast.makeText(RegisterActivity.this, "密码不一致，请确认密码", Toast.LENGTH_SHORT).show();
                }else{
                    boolean flag=registerDao.doInsert(name,pass,sex,age,phone,adress);
                    if (flag){
                        String user_id=registerDao.getUserId(phone);
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("注册成功！您的ID为："+user_id)
                                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                                        finish();
                                    }
                                }).create().show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    public void init(){
        et_name= (EditText) findViewById(R.id.et_name);
        et_pass= (EditText) findViewById(R.id.et_pass);
        et_pass2= (EditText) findViewById(R.id.et_pass2);
        et_age= (EditText) findViewById(R.id.et_age);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_adress= (EditText) findViewById(R.id.et_adress);
        radioGroup= (RadioGroup) findViewById(R.id.rg);
    }
}
