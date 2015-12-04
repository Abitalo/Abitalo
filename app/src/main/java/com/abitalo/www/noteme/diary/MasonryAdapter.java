package com.abitalo.www.noteme.diary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abitalo.www.noteme.R;

import java.util.List;

/**
 * Created by Lancelot on 2015/12/4.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView>{
    private List<Item_Diary> list;


    public MasonryAdapter(List<Item_Diary> list) {
        this.list =list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diary_item, viewGroup, false);
        return new MasonryView(view);
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

    public static class MasonryView extends  RecyclerView.ViewHolder{

        TextView title;
        TextView text;
        TextView date;

        public MasonryView(View itemView){
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.diary_item_title);
            text= (TextView) itemView.findViewById(R.id.diary_item_text);
            date= (TextView) itemView.findViewById(R.id.diary_item_date);
        }

    }

}