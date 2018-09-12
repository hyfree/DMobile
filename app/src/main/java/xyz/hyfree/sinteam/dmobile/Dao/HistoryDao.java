package xyz.hyfree.sinteam.dmobile.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import xyz.hyfree.sinteam.dmobile.databasehelper.DataBaseHelper;
import xyz.hyfree.sinteam.dmobile.entity.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/29.
 */
public class HistoryDao {
    private DataBaseHelper dataBaseHelper;

    SQLiteDatabase sqLiteDatabase;
    public HistoryDao(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }


    /*db.execSQL("create table HISTORY(" +
            "user_id integer not null," +
            "good_name text not null," +
            "good_pic integer not null" +
            "good_price integer not null"+
            ")");*/

    //插入足迹
    public void insertHistory(String uid, String gname, int gpic, String gprice){
        sqLiteDatabase.execSQL("insert into HISTORY ('user_id','good_name','good_pic','good_price') values (?,?,?,?)",
                new Object[]{uid,gname,gpic,gprice});
        Log.e("tt","足迹插入成功");
    }

    //查询足迹
   public List<History> queryHistory(String uid){
       List<History> list=new ArrayList<>();
       Cursor cursor=sqLiteDatabase.rawQuery("select * from HISTORY where user_id=?",new String[]{uid});
       while (cursor.moveToNext()){
           History history=new History();
           history.setUid(cursor.getString(0));
           history.setG_name(cursor.getString(1));
           history.setG_pic(cursor.getInt(2));
           history.setG_price(cursor.getString(3));
           list.add(history);
       }
       return list;
   }




}
