package com.abitalo.www.noteme.mood;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;
import com.tekinarslan.material.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class MoodFragment extends Fragment{
    FloatingActionButton add_btn;

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
        data = new ArrayList<Item_Mood>();
        initDatabase();
        Collections.sort(data, new Comparator<Item_Mood>() {
            @Override
            public int compare(Item_Mood lhs, Item_Mood rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });
        adapter = new MoodListAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }

    private void initDatabase(){
        Cursor cursor=Main.db.rawQuery("select DATE,MOOD from TMOOD where USERNAME='abitalo'",null);
        if(null != cursor){
            while(cursor.moveToNext()){
                data.add(new Item_Mood(cursor.getLong(0),cursor.getString(1)));
            }
            cursor.close();
        }
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
        ContentValues values=new ContentValues();

        values.put("DATE", newItem.getDate().getTimeInMillis());
        values.put("MOOD", newItem.getText());
        values.put("USERNAME","abitalo");
        Main.db.insert("TMOOD", null, values);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        add_btn=(FloatingActionButton)getActivity().findViewById(R.id.mood_add_btn);
        add_btn.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
//        add_btn.bringToFront();
        add_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoodEditorDialog moodEditorDialog = new MoodEditorDialog();
                moodEditorDialog.show(getFragmentManager(),getTag());
            }
        });
    }
}
