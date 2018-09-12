package xyz.hyfree.sinteam.dmobile.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import xyz.hyfree.sinteam.dmobile.databasehelper.DataBaseHelper;

/**
 * Created by Administrator on 2018/8/21.
 */
public class UserDao {
    private DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public UserDao(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }

    public String[] userInfoQuery(String id){
        String[] info=new String[]{};
        String uid,name,pass,sex,age,phone,adress;
        Cursor cursor=sqLiteDatabase.rawQuery("select * from USER where user_id=?",new String[]{id});
        while (cursor.moveToNext()){
            uid=cursor.getString(0);
            name=cursor.getString(1);
            pass=cursor.getString(2);
            sex=cursor.getString(cursor.getColumnIndex("user_sex"));
            age=cursor.getString(cursor.getColumnIndex("user_age"));
            phone=cursor.getString(cursor.getColumnIndex("user_phone"));
            adress=cursor.getString(6);
            info=new String[]{uid,name,pass,sex,age,phone,adress};
        }
        return info;
    }

    public boolean updata(String id, String name, String sex, String age, String phone, String adress){
        sqLiteDatabase.execSQL("update USER set user_name=?, user_sex=?, user_age=?, user_phone=?, user_address=? where user_id=?",new Object[]{
                name,sex,age,phone,adress,id
        });
        return true;
    }
}
