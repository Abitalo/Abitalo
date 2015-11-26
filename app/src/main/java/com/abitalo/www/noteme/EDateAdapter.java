package com.abitalo.www.noteme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.List;

/**
 * Created by asus on 2015/11/25.
 */
public class EDateAdapter extends BaseAdapter {
    private Context context;
    private List<DateText> list;

    public EDateAdapter(Context context, List<DateText> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
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
        holder.date.setText(TimeFormat.format("yyyy.MM.dd",
                        list.get(position).getDate()));
        holder.content.setText(list.get(position).getText());
        return convertView;
    }

    public static class ViewHolder {
        TextView date;
        TextView content;
    }
}

