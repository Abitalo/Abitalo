package com.abitalo.www.noteme.mood;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;

import java.util.List;

/**
 *  Created by zela on 2015/11/25.
 *
 *  Contaning a list<Item_mood>
 *  used for list_view in fragment_mood
 */
public class MoodListAdapter extends BaseAdapter {
    private Context context;
    private List<Item_Mood> list;
    private AlertDialog myDialog;
    private ViewHolder holder;
    //    private Activity parentActivity;
    public MoodListAdapter(Context context, List<Item_Mood> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.mood_item, parent, false);
            holder.date = (TextView) convertView.findViewById(R.id.mood_time);
            holder.content = (TextView) convertView
                    .findViewById(R.id.mood_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(list.get(position).getDateString());
        holder.content.setText(list.get(position).getText());
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myDialog = new AlertDialog.Builder(context).create();
                myDialog.show();
                myDialog.getWindow().setContentView(R.layout.mood_delete_dialog);
                myDialog.getWindow()
                        .findViewById(R.id.mood_delete_positiveButton)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteFromDB(position);
                                list.remove(position);
                                notifyDataSetChanged();
                                myDialog.dismiss();
                            }
                        });
                myDialog.getWindow()
                        .findViewById(R.id.mood_delete_negativeButton)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                return true;
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView date;
        TextView content;
    }

    private void deleteFromDB(int position){
        long timezone=((Item_Mood)list.get(position)).getDate().getTimeInMillis();
        Main.db.delete("TMOOD","DATE=?",new String[]{String.valueOf(timezone)});
    }
}

