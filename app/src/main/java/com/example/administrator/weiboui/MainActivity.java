package com.example.administrator.weiboui;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private ImageView imageView;
    private int offset;
    private ViewPager vp;
    private List<View> viewList;
    private int currItem;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_layout);
        initImageView();
        iniTextView();
        initViewPager();
        super.onCreate(savedInstanceState);
    }

    private void initViewPager() {
        initData();
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        });
        vp.setCurrentItem(0);
        currItem = 0;
        vp.setOnPageChangeListener(new MyOnPagerChangeListener());
    }

    private void initData() {
        viewList = new ArrayList<View>();
        LayoutInflater inflater=getLayoutInflater().from(this);
        View v1=inflater.inflate(R.layout.view_01, null);
        View v2=inflater.inflate(R.layout.view_02,null);
        View v3=inflater.inflate(R.layout.view_03,null);
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);

    }

    private void iniTextView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    private void initImageView() {
        imageView = (ImageView) findViewById(R.id.iv);
//        int bmw= BitmapFactory.decodeResource(getResources(),R.drawable.a).getWidth();
//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int screenW=displayMetrics.widthPixels;
//        offset=(screenW/3-bmw/2)/2;
//        Matrix matrix=new Matrix();
//        matrix.postTranslate(offset,0);
//        imageView.setImageMatrix(matrix);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                vp.setCurrentItem(0);
                currItem=0;
                break;
            case R.id.tv2:
                vp.setCurrentItem(1);
                currItem=1;
                break;
            default:
                vp.setCurrentItem(3);
                currItem=3;
                break;
        }
    }

    private class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener {
        int one;

        public MyOnPagerChangeListener() {
            one=MainActivity.this.getWindowManager().getDefaultDisplay().getWidth()/3;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation=new TranslateAnimation(currItem*one,position*one,0,0);
            animation.setFillAfter(true);
            animation.setDuration(250);
            imageView.startAnimation(animation);
            currItem=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
