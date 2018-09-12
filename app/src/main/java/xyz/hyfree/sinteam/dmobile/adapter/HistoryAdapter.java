package xyz.hyfree.sinteam.dmobile.adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.hyfree.sinteam.dmobile.R;
import xyz.hyfree.sinteam.dmobile.entity.History;

import java.util.List;

/**
 *
 */
public class HistoryAdapter extends BaseAdapter {
    private List<History> list;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ContextWrapper contextWrapper;
    public HistoryAdapter(List<History> list, Context context) {
        this.list = list;
        this.context = context;
        /*contextWrapper=new ContextWrapper()*/
    }

    @Override
    public int getCount() {


        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.layout_history,null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.iv_his_goodspic);
        TextView tv_name= (TextView) convertView.findViewById(R.id.tv_his_goodsname);
        TextView tv_price= (TextView) convertView.findViewById(R.id.tv_his_goodsprice);

        History history=list.get(position);
        imageView.setBackgroundResource(history.getG_pic());
        tv_name.setText(history.getG_name());
        tv_price.setText(history.getG_price());


        return convertView;
    }
}
