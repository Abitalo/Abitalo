package com.abitalo.www.noteme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class MoodFragment extends Fragment {

    // 时间轴列表
    private ListView listView;
    // 数据list
    private List<DateText> data;
    // 列表适配器
    private EDateAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood,container,false);
        listView = (ListView)view.findViewById(R.id.mood_list);
        listView.bringToFront();
        data = new ArrayList<DateText>();
        //添加数据
        addData();
        //将数据按时间排序
        DateComparator comparator = new DateComparator();
        Collections.sort(data, comparator);
        //绑定适配器
        adapter = new EDateAdapter(getActivity(), data);
        listView.setAdapter(adapter);
        return view;
    }
    private void addData() {
        DateText date1 = new DateText("20140710", "Hello world");
        data.add(date1);
        data.add(date1);
        data.add(date1);
        data.add(date1);
        data.add(date1);
        DateText date2 = new DateText("20151125", "This is a test");
        data.add(date2);
        DateText date3 = new DateText("20151126", "Bye");
        data.add(date3);
        DateText date4 = new DateText("20151124", "This is a very very long sentence to test the mood fragment item, and now end!");
        data.add(date4);
    }
}
