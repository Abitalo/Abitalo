package com.abitalo.www.noteme;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.abitalo.www.noteme.alarm.AlarmFragment;
import com.abitalo.www.noteme.diary.DiaryFragment;
import com.abitalo.www.noteme.mood.Item_Mood;
import com.abitalo.www.noteme.mood.MoodEditorDialog;
import com.abitalo.www.noteme.mood.MoodFragment;

import com.tekinarslan.material.SlidingTabLayout;

public class Main extends ActionBarActivity implements MoodEditorDialog.MoodEditListener{//TODO : code optimization

//    private ImageView titleLine;
//    private ViewPagerAdapter mViewPagerAdapter;
//    private ImageButton alarmImage;
//    private ImageButton moodImage;
//    private ImageButton diaryImage;
    private AlarmFragment alarmFragment;
    private MoodFragment moodFragment;
    private DiaryFragment diaryFragment;
    ViewPager mViewPager;

    private String[] titles=new String[]{"时间","心情","日记"};
    private Toolbar toolbar;

    private SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
////            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
//        }
        mViewPager = (ViewPager) findViewById(R.id.pager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));

        slidingTabLayout.setViewPager(mViewPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });

//        init();
        initDatabase();
        //setDefaultFragment();
//        setViewPager();
    }

    private void initDatabase(){
        DatabaseOpenHelper helper=new DatabaseOpenHelper(Main.this,"user.db");
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from author", null);

        if(null != cursor){
            String[] columnNames= cursor.getColumnNames();
            while(cursor.moveToNext()){
                for(String name:columnNames){
                    Log.i("info",name+" : "+cursor.getString(cursor.getColumnIndex(name)));
                }
            }
            cursor.close();
        }
        db.close();
        helper.close();
    }

//    public void setViewPager() {
//        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
//
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mViewPagerAdapter);
//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case TAB_INDEX_ONE: {
//                        resetImage();
//                        // alarmImage.setImageResource(R.drawable.shi);
//                        alarmImage.setImageAlpha(255);
//                        alarmImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        break;
//                    }
//                    case TAB_INDEX_TWO: {
//                        resetImage();
//                        moodImage.setImageAlpha(255);
//                        alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        moodImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        break;
//                    }
//                    case TAB_INDEX_THREE: {
//                        resetImage();
//                        diaryImage.setImageAlpha(255);
//                        alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                        titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        diaryImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                        break;
//                    }
//                }
//            }
//        });
//    }


//    public void init() {
//        alarmImage = (ImageButton) findViewById(R.id.alarm_button);
//        moodImage = (ImageButton) findViewById(R.id.mood_button);
//        diaryImage = (ImageButton) findViewById(R.id.diary_button);
//        titleLine = (ImageView) findViewById(R.id.title_line);
//
//        alarmImage.setImageResource(R.drawable.shi);//设置图片
//        alarmImage.setImageAlpha(255);
//        moodImage.setImageResource(R.drawable.xin);
//        moodImage.setImageAlpha(100);
//        diaryImage.setImageResource(R.drawable.ri);
//        diaryImage.setImageAlpha(100);
//    }

/*    public void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        alarmFragment = new AlarmFragment();
        transaction.replace(R.id.pager, alarmFragment);
        transaction.commit();
    }*/

//    public void bottom_onclick(View v) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        switch (v.getId()) {
//            case R.id.tab_alarm: {
//                mViewPager.setCurrentItem(0);
//                resetImage();
//                alarmImage.setImageAlpha(255);
//                alarmImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                break;
//            }
//            case R.id.tab_mood: {
//                mViewPager.setCurrentItem(1);
//                resetImage();
//                moodImage.setImageAlpha(255);
//                alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                moodImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                diaryImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                break;
//            }
//            case R.id.tab_diary: {
//                mViewPager.setCurrentItem(2);
//                resetImage();
//                diaryImage.setImageAlpha(255);
//                alarmImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                moodImage.setBackgroundColor(Color.parseColor("#a99d8d"));
//                titleLine.setBackgroundColor(Color.parseColor("#d3cabb"));
//                diaryImage.setBackgroundColor(Color.parseColor("#d3cabb"));
//                break;
//            }
//        }
//        transaction.commit();
//    }
//
//    public void resetImage() {
//        alarmImage.setImageAlpha(100);
//        moodImage.setImageAlpha(100);
//        diaryImage.setImageAlpha(100);
//    }

    @Override
    public void moodEditComplete(Item_Mood newItem) {
        moodFragment.update(newItem);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private static final int TAB_INDEX_ONE = 0;
        private static final int TAB_INDEX_TWO = 1;
        private static final int TAB_INDEX_THREE = 2;
        private static final int TAB_COUNT = 3;
        private String[] titles;

        public ViewPagerAdapter(FragmentManager fm , String[] titles) {
            super(fm);
            this.titles=titles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case TAB_INDEX_ONE: {
                    if (null == alarmFragment) {
                        return new AlarmFragment();
                    }
                }
                case TAB_INDEX_TWO: {
                    if (null == moodFragment) {
                        return new MoodFragment();
                    }
                }
                case TAB_INDEX_THREE: {
                    if (null == diaryFragment) {
                        return new DiaryFragment();
                    }
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
