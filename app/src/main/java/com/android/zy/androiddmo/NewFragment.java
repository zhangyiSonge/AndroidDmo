package com.android.zy.androiddmo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2016/3/23.
 */
public class NewFragment extends ListFragment {

    OnListItemClickCallBack callBack;
    ListFragmentAdapter adapter;
    List<String> list;
    int level = 1;//
    int type = R.array.all_types;
    public void setType(int type){
        this.type = type;

        if(adapter!=null){
            adapter.setSelectedItem(-1);
            refreh();
        }
    }

    private void refreh(){
        if(type>0) {
            String[] arr = getResources().getStringArray(type);
            list = Arrays.asList(arr);
        }else {
            list = null;
        }
        adapter.refresh(list);
    }
    public  void setLevel(int level){
        this.level = level;
    }
    public void setCallBack(OnListItemClickCallBack back){
        callBack = back;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         adapter = new ListFragmentAdapter(getContext(),list);
        setListAdapter(adapter);
        refreh();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        adapter.setSelectedItem(position);
        if (callBack!=null){
            String title = list.get(position);
            callBack.itemClick(R.array.type_hotel,level,title);
        }
    }

    public  interface  OnListItemClickCallBack{
         void itemClick(int typeRes,int level,String title);
    }
}
