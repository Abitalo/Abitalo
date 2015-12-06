package com.abitalo.www.noteme.diary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.abitalo.www.noteme.R;

/**
 * Created by Lancelot on 2015/12/7.
 */
public class MasonryView extends  RecyclerView.ViewHolder implements OnClickListener,OnLongClickListener{

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    TextView title;
    TextView text;
    TextView date;

    public MasonryView(View itemView,OnItemClickListener clickListener,OnItemLongClickListener longClickListener){
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.diary_item_title);
        text= (TextView) itemView.findViewById(R.id.diary_item_text);
        date= (TextView) itemView.findViewById(R.id.diary_item_date);

        this.mClickListener = clickListener;
        this.mLongClickListener = longClickListener;


        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mClickListener != null){
            mClickListener.onClick(v, getPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mLongClickListener != null) {
            mLongClickListener.onLongClick(v, getPosition());
        }
        return true;
    }

    public interface OnItemClickListener{
        void onClick(View v,int position);
    }
    public interface OnItemLongClickListener{
        void onLongClick(View v,int position);
    }
}