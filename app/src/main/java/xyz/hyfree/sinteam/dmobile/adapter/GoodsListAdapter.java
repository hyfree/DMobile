package xyz.hyfree.sinteam.dmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.hyfree.sinteam.dmobile.entity.Goods;
import xyz.hyfree.sinteam.dmobile.R;
import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */
public class GoodsListAdapter extends BaseAdapter {
    private List<Goods> list;
    private Context context;
    public GoodsListAdapter(Context context, List<Goods> list) {
        this.list=list;
        this.context=context;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.layout_foods,null);

        TextView tv_foodname,tv_foodprice,tv_qualityperiod;
        ImageView iv_pic;
        tv_foodname= (TextView) convertView.findViewById(R.id.tv_foodname);
        tv_foodprice= (TextView) convertView.findViewById(R.id.tv_foodprice);
        tv_qualityperiod= (TextView) convertView.findViewById(R.id.tv_foodqualityperiod);
        iv_pic= (ImageView) convertView.findViewById(R.id.iv_pic);
        Goods goods=list.get(position);
        iv_pic.setImageResource(goods.getPic());
        tv_foodname.setText(goods.getName());
        tv_foodprice.setText(goods.getPrice());
        tv_qualityperiod.setText(goods.getQualityperiod());

        return convertView;
    }
}
