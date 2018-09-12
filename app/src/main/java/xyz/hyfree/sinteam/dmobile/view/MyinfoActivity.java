package xyz.hyfree.sinteam.dmobile.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import xyz.hyfree.sinteam.dmobile.Dao.UserDao;
import xyz.hyfree.sinteam.dmobile.R;

/**
 *我的信息
 */
public class MyinfoActivity extends Activity {
    private SharedPreferences sharedPreferences;
    private TextView tv_reset;
    private Button btn_cancel,btn_comfrim;
    private TextView name,sex,age,phone,adress;
    private ImageView imageView;
    private EditText et_name,et_age,et_phone,et_adress;
    private RadioButton rbtn_male,rbtn_female,rbtn_flag;
    private LinearLayout ll_info,ll_resetinfo;
    private RadioGroup radiogroup;
    private boolean falg=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        init();

        show();

        //返回按钮
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_info.setVisibility(View.GONE);
                ll_resetinfo.setVisibility(View.VISIBLE);
                show();
            }
        });

        //取消修改
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_info.setVisibility(View.VISIBLE);
                ll_resetinfo.setVisibility(View.GONE);
            }
        });

        //确定修改
        btn_comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                infoUpdata();
                if(falg){
                    show();
                    ll_info.setVisibility(View.VISIBLE);
                    ll_resetinfo.setVisibility(View.GONE);
                    falg=false;
                }


            }
        });


    }

    //初始化
    public void init(){
        name= (TextView) findViewById(R.id.tv_name);
        sex= (TextView) findViewById(R.id.tv_sex);
        age= (TextView) findViewById(R.id.tv_age);
        phone= (TextView) findViewById(R.id.tv_phone);
        adress= (TextView) findViewById(R.id.tv_adress);
        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        imageView= (ImageView) findViewById(R.id.iv_arrow_left);
        et_name= (EditText) findViewById(R.id.et_name);
        rbtn_male= (RadioButton) findViewById(R.id.rbtn_man);
        rbtn_female=(RadioButton) findViewById(R.id.rbtn_weman);
        et_age= (EditText) findViewById(R.id.et_age);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_adress= (EditText) findViewById(R.id.et_adress);
        tv_reset= (TextView) findViewById(R.id.tv_reset);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        btn_comfrim= (Button) findViewById(R.id.btn_confrim);
        ll_info= (LinearLayout) findViewById(R.id.ll_info);
        ll_resetinfo= (LinearLayout) findViewById(R.id.ll_resetinfo);
        radiogroup= (RadioGroup) findViewById(R.id.rg);

    }

    public void show(){
        String userid=sharedPreferences.getString("userid","");
        UserDao userDao=new UserDao(MyinfoActivity.this);
        String[] info=userDao.userInfoQuery(userid);
        name.setText(info[1]);et_name.setText(info[1]);
        age.setText(info[4]);et_age.setText(info[4]);
        phone.setText(info[5]);et_phone.setText(info[5]);
        adress.setText(info[6]);et_adress.setText(info[6]);
        sex.setText(info[3]);
        if(info[3].equals("男")){
            rbtn_male.setChecked(true);
        }else{
            rbtn_female.setChecked(true);
        }
    }

    //执行修改信息
    public void infoUpdata(){
        UserDao userDao=new UserDao(MyinfoActivity.this);
        String userid=sharedPreferences.getString("userid","");
        String name=et_name.getText().toString();
        String age=et_age.getText().toString();
        String phone=et_phone.getText().toString();
        String adress=et_adress.getText().toString();
        String sex="女";
        int id= radiogroup.getCheckedRadioButtonId();
        if(id==R.id.rbtn_man){
            sex="男";
        }
        if("".equals(name)||"".equals(phone)||"".equals(age)||"".equals(adress)){
            Toast.makeText(MyinfoActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
        }else{
            boolean b=userDao.updata(userid,name,sex,age,phone,adress);
            if(b){
                Toast.makeText(MyinfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                falg=true;
            }else{
                Toast.makeText(MyinfoActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
