package com.abitalo.www.noteme.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abitalo.www.noteme.R;
import com.tekinarslan.material.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class DiaryFragment extends Fragment {
    private View view;
    //RecyclerView for implements Masonry Style of diaries.
    private RecyclerView recyclerView;
    //A list containing the data of each diary
    private List<Item_Diary> data;
    //Adapter to fill the RecyclerView
    private MasonryAdapter adapter;
    //Button to write a new diary
    private FloatingActionButton add_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary, container, false);

        init();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        add_btn=(FloatingActionButton)getActivity().findViewById(R.id.diary_add_btn);
        add_btn.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO function :create dialog to write diary
                Toast.makeText(getActivity(), "I'm pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        initData();
        initRecyclerView();


    }
    private void initRecyclerView(){
        recyclerView=(RecyclerView)view.findViewById(R.id.diary_recycler);
        adapter=new MasonryAdapter(data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //TODO setOnItemClickListener

    }

    private void initData() {
        data=new ArrayList<Item_Diary>();
        Item_Diary data1 = new Item_Diary("李奶奶今天不高兴","因为王爷爷有毒","20151205");
        data.add(data1);
        Item_Diary data2 = new Item_Diary("赵日天今天很开心！！！！！！！","因为他发现自己真的是个傻逼","20140515");
        data.add(data2);
        Item_Diary data3 = new Item_Diary("为什么这破玩意要自己输这么多数据","因为无聊","20140515");
        data.add(data3);
        data.add(data3);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data3);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data3);
        data.add(data1);
        data.add(data2);
    }
}

