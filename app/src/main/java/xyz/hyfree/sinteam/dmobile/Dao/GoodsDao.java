package xyz.hyfree.sinteam.dmobile.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import xyz.hyfree.sinteam.dmobile.databasehelper.DataBaseHelper;
import xyz.hyfree.sinteam.dmobile.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */
public class GoodsDao {
    private DataBaseHelper dataBaseHelper;

    SQLiteDatabase sqLiteDatabase;
    public GoodsDao(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }

    public List<Goods> foodQuery(){
        List<Goods> list=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from FOOD",null);
        while (cursor.moveToNext()){
            Goods goods=new Goods();
            goods.setName(cursor.getString(0));
            goods.setPic(cursor.getInt(1));
            goods.setPrice(cursor.getString(2));
            goods.setDate(cursor.getString(3));
            goods.setQualityperiod(cursor.getString(4));
            goods.setNumber(cursor.getString(5));
            list.add(goods);
        }
        return  list;
    }

}
