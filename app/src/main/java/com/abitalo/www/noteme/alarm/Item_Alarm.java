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
    private Calendar sTime=null;
    private Calendar tTime=null;
    private String text=null;

    public Item_Alarm() {
        sTime = Calendar.getInstance();
        tTime = Calendar.getInstance();
        text="";
    }

    public Item_Alarm(String mSTime,String mTTime, String text) {
        form=new SimpleDateFormat("HH:mm");
        this.sTime = Calendar.getInstance();
        this.tTime = Calendar.getInstance();
        try{
            this.sTime.setTime(form.parse(mSTime));
            this.tTime.setTime(form.parse(mTTime));
        }catch(ParseException e){
            Log.i("exception","error in parse date/init");
        }
        this.text = text;
    }

    public String getSatrtTimeString() {
        form=new SimpleDateFormat("HH:mm");
        return form.format(sTime.getTime());
    }

    public String getTerminalTimeString() {
        form=new SimpleDateFormat("HH:mm");
        return form.format(tTime.getTime());
    }

    public String getStartTimeHour() {
        form=new SimpleDateFormat("HH");
        return form.format(sTime.getTime());
    }

    public String getTerminalTimeHour() {
        form=new SimpleDateFormat("HH");
        return form.format(tTime.getTime());
    }

    public String getStartTimeMinute() {
        form=new SimpleDateFormat("mm");
        return form.format(sTime.getTime());
    }

    public String getTerminalTimeMinute() {
        form=new SimpleDateFormat("mm");
        return form.format(tTime.getTime());
    }

    public Calendar getStartTime(){
        return sTime;
    }

    public Calendar getTerminalTime(){
        return tTime;
    }

    public void setStartTimeString(String date) {
        form=new SimpleDateFormat("HH:mm");
        try{
            this.sTime.setTime(form.parse(date));
        }catch(ParseException e){
            Log.i("exception","error in parse date/set");
        }
    }

    public void setTerminalTimeString(String date) {
        form=new SimpleDateFormat("HH:mm");
        try{
            this.tTime.setTime(form.parse(date));
        }catch(ParseException e){
            Log.i("exception","error in parse date/set");
        }
    }

    public void setStartTime(Calendar date){
        this.sTime=date;
    }

    public void setTerminalTime(Calendar date){
        this.tTime=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

