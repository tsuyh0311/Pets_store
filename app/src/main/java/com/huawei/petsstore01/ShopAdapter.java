package com.huawei.petsstore01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private LinkedList<shop> shopList;

    public ShopAdapter(Context context, LinkedList<shop> shopList) {
        this.context = context;
        this.shopList = shopList;
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item_01, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView space = (TextView) convertView.findViewById(R.id.space);
        ImageView pic1 = (ImageView) convertView.findViewById(R.id.pic1);
        ImageView pic2 = (ImageView) convertView.findViewById(R.id.pic2);
        ImageView pic3 = (ImageView) convertView.findViewById(R.id.pic3);
        ImageView pic4 = (ImageView) convertView.findViewById(R.id.pic4);
        name.setText(shopList.get(position).getNanme());
        space.setText(shopList.get(position).getSpace());
        pic1.setBackgroundResource(shopList.get(position).getPic1());
        pic2.setBackgroundResource(shopList.get(position).getPic2());
        pic3.setBackgroundResource(shopList.get(position).getPic3());
        pic4.setBackgroundResource(shopList.get(position).getPic4());
        return convertView;

    }


}
