package com.abitalo.www.noteme.alarm;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Outline;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    //当前显示的item
    private Item_Alarm currentItem;
    //当数据为0时，显示的提醒item
    private Item_Alarm initItem;
    private Handler tickHandler ;
    private boolean hasData;
    AlertDialog myDialog;
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
//        Log.v("logcat","zela Create" );
        //设置提醒item
        initItem = new Item_Alarm("00:00","00:00","做个计划吧！");
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
        //hasData默认为false
        hasData = false;
        //添加假数据
        addFakeData();
        DateComparator comparator = new DateComparator();
        Collections.sort(data, comparator);
        if(hasData){
            currentItem = data.get(0);
            setClock(currentItem);
            setEvent(currentItem);
            setTime(currentItem);
        }
        else {
            setClock(initItem);
            setEvent(initItem);
            setTime(initItem);
        }
        //记得每次设定事件后要调用该函数，修改左右按钮的状态以及hasData状态
        changeButtonState();
        left.setOnClickListener(this);
        right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(!hasData) return;
        int id=data.indexOf(currentItem);
        switch (v.getId()){
            case R.id.alarm_left:{
                if(id>0) {
                    currentItem = data.get(id - 1);
                    clock.setEventTime(currentItem);
                    setEvent(currentItem);
                    setTime(currentItem);
                }
                break;
            }
            case R.id.alarm_right:{
                if(id<data.size()-1){
                    currentItem = data.get(id+1);
                    clock.setEventTime(currentItem);
                    setEvent(currentItem);
                    setTime(currentItem);
                }
                break;
            }
        }
        changeButtonState();
    }

    private  void setClock(Item_Alarm itemAlarm){
        clock.setEventTime(itemAlarm);
        clock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        clock.setDialAlpha(235);
                        break;
                    case MotionEvent.ACTION_UP:
                        clock.setDialAlpha(255);
                        break;
                }
                return false;
            }
        });
        clock.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(getActivity(), "OnLongClickListener事件", Toast.LENGTH_SHORT).show();
                if(!hasData)
                    return  true;
                myDialog = new AlertDialog.Builder(getActivity()).create();
                myDialog.show();
                myDialog.getWindow().setContentView(R.layout.alarm_dialog);
                myDialog.getWindow()
                        .findViewById(R.id.positiveButton)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = data.indexOf(currentItem);
                                if(id+1<data.size()){
                                    Item_Alarm tmp = data.get(id+1);
                                    data.remove(currentItem);
                                    currentItem = tmp;
                                    setTime(currentItem);
                                    clock.setEventTime(currentItem);
                                    setEvent(currentItem);
                                }
                                else if(id-1>=0){
                                    Item_Alarm tmp = data.get(id-1);
                                    data.remove(currentItem);
                                    currentItem=tmp;
                                    setTime(currentItem);
                                    clock.setEventTime(currentItem);
                                    setEvent(currentItem);
                                }
                                else{
                                    data.remove(currentItem);
                                    currentItem = null;
                                    setTime(initItem);
                                    clock.setEventTime(initItem);
                                    setEvent(initItem);
                                }
                                changeButtonState();
                                myDialog.dismiss();
                            }
                        });
                myDialog.getWindow()
                        .findViewById(R.id.negativeButton)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                clock.setDialAlpha(255);
                return true;
            }
        });
    }

    private  void changeButtonState(){
        if(0 == data.size()) hasData = false;
        else hasData = true;
        if(!hasData || data.size() == 1){
            left.setImageResource(R.drawable.btn_unclk_left);
            left.setClickable(false);
            right.setImageResource(R.drawable.btn_unclk_right);
            right.setClickable(false);
        }
        else{
            right.setImageResource(R.drawable.right_button_selected);
            right.setClickable(true);
            left.setImageResource(R.drawable.left_button_selected);
            left.setClickable(true);
            if(data.indexOf(currentItem) == 0){
                left.setImageResource(R.drawable.btn_unclk_left);
                left.setClickable(false);
            }
            else if(data.indexOf(currentItem) == data.size()-1){
                right.setImageResource(R.drawable.btn_unclk_right);
                right.setClickable(false);
            }
        }
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
        itemAlarm.setText("吃饭、睡觉、打豆豆、学习、洗澡、洗漱、巴拉拉能量、仙朵拉、魔法鞋、古娜拉黑暗之神、沙罗沙罗、黑魔旋风。");
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
        hasData=true;
    }
    private class DateComparator implements Comparator<Item_Alarm> {

        @Override
        public int compare(Item_Alarm lhs, Item_Alarm rhs) {
            return -rhs.getStartTime().compareTo(lhs.getStartTime());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        do not forget call removeCallbacks
        tickHandler.removeCallbacks(tickRunnable);
//        Log.v("logcat", "zela Destory");
    }
}
