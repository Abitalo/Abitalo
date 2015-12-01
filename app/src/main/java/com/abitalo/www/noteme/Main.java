package com.abitalo.www.noteme;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abitalo.www.noteme.alarm.*;
import com.abitalo.www.noteme.diary.*;
import com.abitalo.www.noteme.mood.*;

public class Main extends Activity {//TODO : code optimization
    private static final int TAB_INDEX_ONE = 0;
    private static final int TAB_INDEX_TWO = 1;
    private static final int TAB_INDEX_THREE = 2;
    private static final int TAB_COUNT = 3;
    private AlarmFragment alarmFragment;
    private MoodFragment moodFragment;
    private DiaryFragment diaryFragment;
    private ImageButton alarmImage;
    private ImageButton moodImage;
    private ImageButton diaryImage;
    private ImageView titleLine;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        //setDefaultFragment();
        setViewPager();
    }

    public void setViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case TAB_INDEX_ONE: {
                        resetImage();
                        // alarmImage.setImageResource(R.drawable.shi);
                        alarmImage.setImageAlpha(255);
                        alarmImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                        moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        break;
                    }
                    case TAB_INDEX_TWO: {
                        resetImage();
                        moodImage.setImageAlpha(255);
                        alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        moodImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                        diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        break;
                    }
                    case TAB_INDEX_THREE: {
                        resetImage();
                        diaryImage.setImageAlpha(255);
                        alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                        diaryImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                        break;
                    }
                }
            }
        });
    }


    public void init() {
        alarmImage = (ImageButton) findViewById(R.id.alarm_button);
        moodImage = (ImageButton) findViewById(R.id.mood_button);
        diaryImage = (ImageButton) findViewById(R.id.diary_button);
        titleLine = (ImageView) findViewById(R.id.title_line);

        alarmImage.setImageResource(R.drawable.shi);//设置图片
        alarmImage.setImageAlpha(255);
        moodImage.setImageResource(R.drawable.xin);
        moodImage.setImageAlpha(100);
        diaryImage.setImageResource(R.drawable.ri);
        diaryImage.setImageAlpha(100);
    }

/*    public void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        alarmFragment = new AlarmFragment();
        transaction.replace(R.id.pager, alarmFragment);
        transaction.commit();
    }*/

    public void bottom_onclick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.tab_alarm: {
                mViewPager.setCurrentItem(0);
                resetImage();
                alarmImage.setImageAlpha(255);
                alarmImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                break;
            }
            case R.id.tab_mood: {
                mViewPager.setCurrentItem(1);
                resetImage();
                moodImage.setImageAlpha(255);
                alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                moodImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                break;
            }
            case R.id.tab_diary: {
                mViewPager.setCurrentItem(2);
                resetImage();
                diaryImage.setImageAlpha(255);
                alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
                diaryImage.setBackgroundColor(Color.parseColor("#d3cabb"));
                break;
            }
        }
        transaction.commit();
    }

    public void resetImage() {
        alarmImage.setImageAlpha(100);
        moodImage.setImageAlpha(100);
        diaryImage.setImageAlpha(100);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case TAB_INDEX_ONE: {
                    if (null == alarmFragment) {
                        alarmFragment = new AlarmFragment();
                    }
                    return alarmFragment;
                }
                case TAB_INDEX_TWO: {
                    if (null == moodFragment) {
                        moodFragment = new MoodFragment();
                    }
                    return moodFragment;
                }
                case TAB_INDEX_THREE: {
                    if (null == diaryFragment) {
                        diaryFragment = new DiaryFragment();
                    }
                    return diaryFragment;
                }
            }
            throw new IllegalStateException("No fragment at position" + i);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
