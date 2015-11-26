package com.abitalo.www.noteme;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

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
    private ImageButton titleLine;
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
                        alarmImage.setBackgroundColor(Color.parseColor("#bbdfdb"));
                        moodImage.setBackgroundColor(Color.parseColor("#f6938d"));
                        titleLine.setBackgroundColor(Color.parseColor("#f6938d"));
                        diaryImage.setBackgroundColor(Color.parseColor("#bbdfdb"));
                        break;
                    }
                    case TAB_INDEX_THREE: {
                        resetImage();
                        diaryImage.setImageAlpha(255);
                        alarmImage.setBackgroundColor(Color.parseColor("#ebe6b9"));
                        moodImage.setBackgroundColor(Color.parseColor("#ebe6b9"));
                        titleLine.setBackgroundColor(Color.parseColor("#e0bd95"));
                        diaryImage.setBackgroundColor(Color.parseColor("#e0bd95"));
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
        titleLine = (ImageButton) findViewById(R.id.title_line);

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
                alarmImage.setBackgroundColor(Color.parseColor("#bbdfdb"));
                moodImage.setBackgroundColor(Color.parseColor("#f6938d"));
                titleLine.setBackgroundColor(Color.parseColor("#f6938d"));
                diaryImage.setBackgroundColor(Color.parseColor("#bbdfdb"));
                break;
            }
            case R.id.tab_diary: {
                mViewPager.setCurrentItem(2);
                resetImage();
                diaryImage.setImageAlpha(255);
                alarmImage.setBackgroundColor(Color.parseColor("#ebe6b9"));
                moodImage.setBackgroundColor(Color.parseColor("#ebe6b9"));
                titleLine.setBackgroundColor(Color.parseColor("#e0bd95"));
                diaryImage.setBackgroundColor(Color.parseColor("#e0bd95"));
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
