package com.android.zy.androiddmo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class ListFragmentAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    int selectedPosition = -1;
    public ListFragmentAdapter(Context context,List<String> list){
        this.context =context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    public void  setSelectedItem(int position){
        selectedPosition = position;
        notifyDataSetChanged();
    }
    public void refresh(List<String> list){
        this.list = list;
        selectedPosition = -1;
        notifyDataSetChanged();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(list.get(position));
        tv.setPadding(40,40,40,40);
        if (selectedPosition==position){
            tv.setBackgroundColor(Color.RED);
        }else {
            tv.setBackgroundColor(Color.WHITE);
        }
        return tv;
    }
}
