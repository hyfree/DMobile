package xyz.hyfree.sinteam.dmobile.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import xyz.hyfree.sinteam.dmobile.databasehelper.DataBaseHelper;

/**
 * Created by Administrator on 2018/8/22.
 */
public class LoginDao {

    private DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public LoginDao(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }

    public String userQuery(String id, String pass){
        String name="";
        Cursor cursor=sqLiteDatabase.rawQuery("select * from USER where user_id=?",new String[]{id});
        while (cursor.moveToNext()){
            if(pass.equals(cursor.getString(2))){
                name= cursor.getString(1);
            }
        }
        return name;
    }
}
