package com.android.zy.androiddmo.utils;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * Created by zhangyi on 2016/4/20.
 */
public class OKHttpUtils {
    private final  MediaType MEDIATYPE_PNG = MediaType.parse("image/png");
    private final  OkHttpClient client = new OkHttpClient();
    Request.Builder builder ;
    private static OKHttpUtils utils;
    public static OKHttpUtils getInstance(){
        if (utils == null){
            utils = new OKHttpUtils();
        }
        return  utils;
    }
    /**
     * 做GET请求操作
     * @param url 接口地址，不含参数
     * @return
     */
    public void doGet(String url,List<NameValuePair> params,OKCallBack back){
        builder = new Request.Builder();
        builder.url(url+buildGetUrl(params));
        excute(back);
    }
    /**
     * 上传图片
     * @param url 接口地址
     * @param params 参数，如果没有参数 传递 null
     * @param path 图片绝对地址
     * @return
     */
    public void upLoadImage(String url,List<NameValuePair> params,String path,OKCallBack
            back){
         builder = new Request.Builder();
        builder.url(url);
        MultipartBody.Builder body = new  MultipartBody.Builder();
        if (params!=null && !params.isEmpty()){
            for (NameValuePair pair : params){
               body.addFormDataPart(pair.getKey(),pair.getValue());
            }
        }
        File file = new File(path);
        body.addFormDataPart("mFile",file.getName(),RequestBody.create(MEDIATYPE_PNG,file));
        builder.post(body.build());
       excute(back);
    }
    /**
     * 做POST请求操作
     * @param url post请求地址
     * @param params 参数
     * @return
     */
    public void doPost(String url,List<NameValuePair> params,OKCallBack back){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
       if (params!=null && !params.isEmpty()) {
           FormBody.Builder body = new FormBody.Builder();
           for (NameValuePair pair : params) {
                body.add(pair.getKey(),pair.getValue());
           }
           builder.post(body.build());
       }

      excute(back);
    }
    Handler handler = new Handler();
    /**
     * 执行请求
     * @param back
     */

    private  void excute(final OKCallBack back){

       client.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               if (back!=null) {
                                   back.onFailure("网络请求出错");
                               }
                            }
                        });
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
           final  String result = response.body().string();
              handler.post(new Runnable() {
                  @Override
                  public void run() {
                      if (back!=null) {
                          back.onResponse(result);
                      }
                  }
              });
            }
        });
    }
    //组装Get 请求的 url 后缀
    private String buildGetUrl(List<NameValuePair> list){
       String result = "" ;
        if (list != null && !list.isEmpty()){
           StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            for (NameValuePair pair : list){
                if (isFirst){
                    sb.append("?");
                    isFirst =false;
                }else {
                    sb.append("&");
                }
                sb.append(pair.getKey());
                sb.append("=");
                sb.append(pair.getValue());
            }
            result = sb.toString();
        }
        return result;
    }

    /**
     * 回调接口
     */
    public  interface OKCallBack{
         void onFailure(String message);
        void onResponse(String body);
    }
    /**
     * 键值对
     */
    public static  class NameValuePair{
        String key;
        String value;
        public NameValuePair(String key,String value){
            this.key = key;
            this.value = value;
        }
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
