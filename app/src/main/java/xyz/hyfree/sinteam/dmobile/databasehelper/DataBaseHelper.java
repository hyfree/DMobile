package xyz.hyfree.sinteam.dmobile.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import xyz.hyfree.sinteam.dmobile.R;

/**
 * Created by Administrator on 2018/8/22.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "my.db", null, 1);
    }

    private int[] pic=new int[]{R.drawable.food_mianbao,R.drawable.food_mian,R.drawable.food_xunroupian,
            R.drawable.food_yangrou,R.drawable.food_yuebing,R.drawable.food_huasheng};
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER (" +
                "user_id integer primary key autoincrement," +
                "user_name text not null," +
                "user_pass text not null," +
                "user_sex text not null," +
                "user_age integer not null," +
                "user_phone text unique not null," +
                "user_address text not null)");

        db.execSQL("insert into USER ('user_name','user_pass','user_sex','user_age','user_phone','user_address') values (?,?,?,?,?,?)",
                new Object[]{"administer","123456","男",0,"12345678910","中国大陆"});

        db.execSQL("CREATE TABLE FOOD (" +
                "food_name text primary key," +
                "food_pic integer not null," +
                "food_price integer not null," +
                "food_date text not null," +
                "food_qualityperiod text not null," +
                "food_number text not null)");

        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"面包",pic[0],"15","2018-8-14","3","1002"});
        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"面条",pic[1],"32","2018-5-14","12","156"});
        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"熏肉片",pic[2],"45","2018-8-21","1","152"});
        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"羊肉",pic[3],"30","2018-8-09","2","213"});
        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"月饼",pic[4],"18","2018-8-20","3","1002"});
        db.execSQL("insert into FOOD ('food_name','food_pic','food_price','food_date','food_qualityperiod','food_number') values (?,?,?,?,?,?)",
                new Object[]{"花生",pic[5],"15","2018-8-19","5","192"});


        db.execSQL("create table HISTORY(" +
                "user_id integer not null," +
                "good_name text not null," +
                "good_pic integer not null," +
                "good_price integer not null"+
                ")");

        /*db.execSQL("CREATE TABLE SHOPPINGCAR (" +
                "goods_name text primary key," +
                "goods_name text primary key," +
                "goods_pic integer not null," +
                "food_price text not null," +
                "food_date text not null," +
                "food_qualityperiod text not null," +
                "food_number text not null)");*/
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
