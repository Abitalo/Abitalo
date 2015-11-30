package com.abitalo.www.noteme.alarm;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;
import com.abitalo.www.noteme.Varible;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class AlarmFragment extends Fragment implements View.OnClickListener {
    private ImageButton list;
    private ImageButton left;
    private ImageButton right;

    private TextView eventContent;
    private TextView eventTime;

    public Clock clock;
    private ArrayList<Item_Alarm> data;
    private View view;
    private Item_Alarm currentItem;
    private Main mActivity;
    private Handler tickHandler ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tickHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case Varible.VALIDATE_CLOCK:{
                        clock.postInvalidate();
                    }
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alarm,container,false);
        init(view);
        tickHandler.post(tickRunnable);
        return view;
    }

    private Runnable tickRunnable = new Runnable() {
        public void run() {
            Message msg = new Message();
            msg.what = Varible.VALIDATE_CLOCK;
            tickHandler.sendMessage(msg);
            tickHandler.postDelayed(tickRunnable, 1000);
        }
    };

    private void init(View view){
        data = new ArrayList<Item_Alarm>();
        left= (ImageButton) view.findViewById(R.id.alarm_left);
        right = (ImageButton) view.findViewById(R.id.alarm_right);
        clock = (Clock) view.findViewById(R.id.clock);
        eventContent = (TextView) view.findViewById(R.id.event_text);
        eventTime = (TextView) view.findViewById(R.id.event_time);

        addFakeData();
        DateComparator comparator = new DateComparator();
        Collections.sort(data, comparator);
        currentItem = data.get(0);
        setClock(currentItem);
        setEvent(currentItem);
        setTime(currentItem);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int id=data.indexOf(currentItem);
        switch (v.getId()){
            case R.id.alarm_left:{
                if(id>0){
                    currentItem = data.get(id-1);
                    clock.setEventTime(currentItem);
                    setEvent(currentItem);
                    setTime(currentItem);
                    if(data.size()-1 == id){
                        right.setImageResource(R.drawable.right_button_selected);
                        right.setClickable(true);
                    }
                    id--;
                }
                if(0 == id){
                    left.setImageResource(R.drawable.btn_unclk_left);
                    left.setClickable(false);
                }
                break;
            }
            case R.id.alarm_right:{
                if(id<data.size()-1){
                    currentItem = data.get(id+1);
                    clock.setEventTime(currentItem);
                    setEvent(currentItem);
                    setTime(currentItem);
                    if(0 == id){
                        left.setImageResource(R.drawable.left_button_selected);
                        left.setClickable(true);
                    }
                    id++;
                }
                if(data.size()-1 == id){
                    right.setImageResource(R.drawable.btn_unclk_right);
                    right.setClickable(false);
                }
                break;
            }
        }
    }

    private  void setClock(Item_Alarm itemAlarm){
        clock.setEventTime(itemAlarm);
    }

    private void setEvent(Item_Alarm itemAlarm){
        eventContent.setText(itemAlarm.getText());
    }

    private void setTime(Item_Alarm itemAlarm){
        eventTime.setText("起始时间：" + itemAlarm.getSatrtTimeString()+"\n结束时间："+itemAlarm.getTerminalTimeString()+"\n");
    }

    private void addFakeData(){
        Item_Alarm itemAlarm = new Item_Alarm();
        itemAlarm.setStartTimeString("13:12");
        itemAlarm.setTerminalTimeString("13:42");
        itemAlarm.setText("吃饭");
        data.add(itemAlarm);
        itemAlarm = new Item_Alarm();
        itemAlarm.setStartTimeString("16:30");
        itemAlarm.setTerminalTimeString("20:30");
        itemAlarm.setText("学习");
        data.add(itemAlarm);
        itemAlarm = new Item_Alarm();
        itemAlarm.setStartTimeString("22:30");
        itemAlarm.setTerminalTimeString("23:00");
        itemAlarm.setText("洗漱");
        data.add(itemAlarm);
    }
    private class DateComparator implements Comparator<Item_Alarm> {

        @Override
        public int compare(Item_Alarm lhs, Item_Alarm rhs) {
            return -rhs.getStartTime().compareTo(lhs.getStartTime());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
