package com.abitalo.www.noteme;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.abitalo.www.noteme.alarm.AlarmFragment;
import com.abitalo.www.noteme.diary.DiaryFragment;
import com.abitalo.www.noteme.mood.MoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/11/30.
 */
public class Main2 extends FragmentActivity {

    /** 页面list **/
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    /** 页面title list **/
    List<String>   titleList    = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vp = (ViewPager)findViewById(R.id.pager);
        fragmentList.add(new AlarmFragment());
        fragmentList.add(new MoodFragment());
        fragmentList.add(new DiaryFragment());
        titleList.add("title 1 ");
        titleList.add("title 2 ");
        titleList.add("title 3 ");
        vp.setAdapter(new myPagerAdapter(getFragmentManager(), fragmentList, titleList));
    }

    /**
     * 定义适配器
     *
     * @author trinea.cn 2012-11-15
     */
    class myPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String>   titleList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList){
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
