package com.abitalo.www.noteme.diary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abitalo.www.noteme.R;

import java.util.List;

/**
 * Created by Lancelot on 2015/12/4.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryView>{
    private List<Item_Diary> list;

    private MasonryView.OnItemClickListener mItemClickListener;
    private MasonryView.OnItemLongClickListener mItemLongClickListener;

    public MasonryAdapter(List<Item_Diary> list) {
        this.list =list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diary_item, viewGroup, false);
        return new MasonryView(view,mItemClickListener,mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(MasonryView masonryView, int position) {
        masonryView.title.setText(list.get(position).getTitle());
        masonryView.text.setText(list.get(position).getText());
        masonryView.date.setText(list.get(position).getDateString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(MasonryView.OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MasonryView.OnItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }




}