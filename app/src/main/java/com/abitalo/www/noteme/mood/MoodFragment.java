package com.abitalo.www.noteme.mood;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.abitalo.www.noteme.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class MoodFragment extends Fragment {

    // 时间轴列表
    private ListView listView;
    // 数据list
    private List<Item_Mood> data;
    // 列表适配器
    private EDateAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood,container,false);

        listView = (ListView)view.findViewById(R.id.mood_list);
        listView.bringToFront();
        data = new ArrayList<Item_Mood>();
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
        Item_Mood date1 = new Item_Mood("20140710", "Hello world");
        data.add(date1);
        data.add(date1);
        data.add(date1);
        data.add(date1);
        data.add(date1);
        Item_Mood date2 = new Item_Mood("20151125", "This is a test");
        data.add(date2);
        Item_Mood date3 = new Item_Mood("20151126", "Bye");
        data.add(date3);
        Item_Mood date4 = new Item_Mood("20151124", "This is a very very long sentence to test the mood fragment item, and now end!");
        data.add(date4);
    }
    private class DateComparator implements Comparator<Item_Mood> {

        @Override
        public int compare(Item_Mood lhs, Item_Mood rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
        }
    }
}
