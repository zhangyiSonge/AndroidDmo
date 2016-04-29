package com.android.zy.androiddmo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.zy.androiddmo.utils.OKHttpUtils;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewFragment
        .OnListItemClickCallBack ,OKHttpUtils.OKCallBack{
    LinearLayout container;
    NewFragment levl1Fragment;
    NewFragment levl2Fragment;
    FragmentManager manager;
    FragmentTransaction tran;
    String result;
    TextView tvRessult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        container = (LinearLayout) findViewById(R.id.linear);
        tvRessult  = (TextView) findViewById(R.id.result);
        manager = getSupportFragmentManager();
        List<OKHttpUtils.NameValuePair> list = new ArrayList<>();
        list.add(new OKHttpUtils.NameValuePair("access_token","2.00cY5xTC0nhTud64aa88553ai44RBB"));
        OKHttpUtils.getInstance().doGet("https://api.weibo.com/2/statuses/public_timeline.json",
                list,this);
    }

    public  void show(View v){
      /*  container.setVisibility(View.VISIBLE);
      if(levl1Fragment==null){
        levl1Fragment = new NewFragment();
        levl1Fragment.setLevel(1);
        levl1Fragment.setCallBack(this);
        tran = manager.beginTransaction();
        tran.add(R.id.ly1,levl1Fragment);
        tran.commit();
      }
        levl1Fragment.setType(R.array.all_types);
        if(levl2Fragment!=null){
            levl2Fragment.setType(0);
        }*/
        List<OKHttpUtils.NameValuePair> list = new ArrayList<>();
        list.add(new OKHttpUtils.NameValuePair("access_token","2.00cY5xTC0nhTud64aa88553ai44RBB"));
        list.add(new OKHttpUtils.NameValuePair("status","status"));

        OKHttpUtils.getInstance().doPost("https://api.weibo.com/2/statuses/update.json",list,this);
    }
    @Override
    public void itemClick(int typeRes, int level, String title) {
        if (level ==1){
            result = title;
            if (levl2Fragment==null){
                levl2Fragment = new NewFragment();
                levl2Fragment.setLevel(2);
                levl2Fragment.setCallBack(this);
                tran = manager.beginTransaction();
                tran.add(R.id.ly2,levl2Fragment);
                tran.commit();
            }
            levl2Fragment.setType(typeRes);
        }else {
            result+=title;
            container.setVisibility(View.GONE);
            tvRessult.setText(result);
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onResponse(String body) {
    tvRessult.setText(body);
        Log.i("result",body);
    }
}
