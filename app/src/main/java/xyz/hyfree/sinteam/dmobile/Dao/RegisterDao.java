package xyz.hyfree.sinteam.dmobile.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import xyz.hyfree.sinteam.dmobile.databasehelper.DataBaseHelper;

/**
 * Created by Administrator on 2018/8/22.
 */
public class RegisterDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RegisterDao(Context context){
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();

    }

    public boolean doInsert(String name, String pass, String sex, String age, String phone, String address){
        sqLiteDatabase.execSQL("insert into USER ('user_name','user_pass','user_sex','user_age','user_phone','user_address') values (?,?,?,?,?,?)",
                new Object[]{name,pass,sex,age,phone,address});

        return true;
    }

    public String getUserId(String phone){
        String name="";
        Cursor cursor=sqLiteDatabase.rawQuery("select * from USER where user_phone=?",new String[]{phone});
        while (cursor.moveToNext()){
            name= cursor.getString(0);
        }
        return name;
    }
}
