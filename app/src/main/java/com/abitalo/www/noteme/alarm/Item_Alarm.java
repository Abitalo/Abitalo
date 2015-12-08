package com.abitalo.www.noteme.alarm;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by asus on 2015/11/25.
 */
public class Item_Alarm {
    private static SimpleDateFormat form=null;
    private Calendar time =null;
    private String text=null;

    public Item_Alarm() {
        time = Calendar.getInstance();
        text="";
    }

    public Item_Alarm(String mSTime, String text) {
        form=new SimpleDateFormat("HH:mm");
        this.time = Calendar.getInstance();
        try{
            this.time.setTime(form.parse(mSTime));
        }catch(ParseException e){
            Log.i("exception","error in parse date/init");
        }
        this.text = text;
    }

    public String getSatrtTimeString() {
        form=new SimpleDateFormat("HH:mm");
        return form.format(time.getTime());
    }

    public String getStartTimeHour() {
        form=new SimpleDateFormat("HH");
        return form.format(time.getTime());
    }

    public String getStartTimeMinute() {
        form=new SimpleDateFormat("mm");
        return form.format(time.getTime());
    }

    public Calendar getStartTime(){
        return time;
    }

    public void setStartTimeString(String date) {
        form=new SimpleDateFormat("HH:mm");
        try{
            this.time.setTime(form.parse(date));
        }catch(ParseException e){
            Log.i("exception","error in parse date/set");
        }
    }

    public void setStartTime(Calendar date){
        this.time =date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

