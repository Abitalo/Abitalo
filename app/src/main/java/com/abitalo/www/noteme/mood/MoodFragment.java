package com.abitalo.www.noteme.mood;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.abitalo.www.noteme.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class MoodFragment extends Fragment implements MoodEditorDialog.MoodEditListener{
    ImageButton add_btn;
    // 时间轴列表
    private ListView listView;
    // 数据list
    private List<Item_Mood> data;
    // 列表适配器
    private MoodListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood,container,false);

        initMoodList(view);
        return view;
    }

    private void initMoodList(View view){
        listView = (ListView)view.findViewById(R.id.mood_list);
//        listView.bringToFront();
        data = new ArrayList<Item_Mood>();
        addData();
        Collections.sort(data, new Comparator<Item_Mood>() {
            @Override
            public int compare(Item_Mood lhs, Item_Mood rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });
        adapter = new MoodListAdapter(getActivity(), data);
        listView.setAdapter(adapter);
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

    public void update(Item_Mood newItem) {
        data.add(newItem);
        Collections.sort(data, new Comparator<Item_Mood>() {
            @Override
            public int compare(Item_Mood lhs, Item_Mood rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        add_btn=(ImageButton)getActivity().findViewById(R.id.mood_menu);
//        add_btn.bringToFront();
        add_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoodEditorDialog moodEditorDialog = new MoodEditorDialog();
                moodEditorDialog.show(getFragmentManager(), getTag());
            }
        });
    }

    @Override
    public void moodEditComplete(Item_Mood newItem){
        data.add(newItem);
        adapter.notifyDataSetChanged();
    }
}
