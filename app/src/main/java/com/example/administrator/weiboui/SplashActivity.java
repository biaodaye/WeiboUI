package com.example.administrator.weiboui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/6/27.
 */
public class SplashActivity extends Activity {
    private static final int GO_GUIDE=1000;
    private static final int GO_HOME=1001;
    private boolean isFirst;
    private String prefName="MyPref";
    private int delay=3000;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_GUIDE:
                    goGuide();
                    break;
                case GO_HOME:
                    goHome();
                    break;
            }
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.splash_layout);
        SharedPreferences spf=getSharedPreferences(prefName,MODE_PRIVATE);
        isFirst=spf.getBoolean("isFirst",true);
        if (isFirst){
            mHandler.sendEmptyMessageDelayed(GO_GUIDE,delay);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_HOME,delay);
        }
        super.onCreate(savedInstanceState);
    }
    private void goHome() {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    private void goGuide() {
        Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
