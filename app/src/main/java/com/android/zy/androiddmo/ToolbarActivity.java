package com.android.zy.androiddmo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ToolbarActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        tvInfo = (TextView) findViewById(R.id.tvResult);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigate);
      /* 如果使用 ToolBar  并且让 ToolBar 上显示信息 ，则、设置如下
       Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,mToolbar,R.string.app_name,R.string.action_settings);
        toggle.syncState();//初始化*/
        navigationView.setNavigationItemSelectedListener(itemSelectedListener);//为item 设置 监听
        drawerLayout.addDrawerListener(listener);//抽屉拖动的监听
    }

    private void test(){
        String result = "张三这里是红色的，我想有下划线，我被划掉了.[哈哈]";
        SpannableString spannable = new SpannableString(result);
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);//设置前景色
        UnderlineSpan underlineSpan = new UnderlineSpan();
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        ImageSpan imag = new ImageSpan(drawable);
        spannable.setSpan(span,4,7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        int index = result.indexOf("有");
        int end = result.indexOf("线");
        spannable.setSpan(underlineSpan,index,end+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannable.setSpan(strikethroughSpan,end+3,end+6,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        index = result.indexOf("[");
        end = result.indexOf("]");
        spannable.setSpan(imag, index, end + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvInfo.setText(spannable);
    }
    NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            drawerLayout.closeDrawers();
            return false;
        }
    };
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}
