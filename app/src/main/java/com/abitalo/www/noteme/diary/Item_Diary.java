package com.abitalo.www.noteme.diary;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lancelot on 2015/12/4.
 */
public class Item_Diary {
    private static SimpleDateFormat form=null;
    private String title=null;
    private String text=null;
    private Calendar date=null;


    public Item_Diary(String title,String text,Calendar date){
        this.title=title;
        this.text=text;
        this.date=date;
    }
    public Item_Diary(String title,String text,String dateString) {//for use of test
        form=new SimpleDateFormat("yyyyMMdd");

        this.date = Calendar.getInstance();
        try{
            this.date.setTime(form.parse(dateString));
        }catch(ParseException e){
            Log.i("exception", "error in parse date/init");
        }

        this.text = text;
        this.title = title;
    }

    public String getDateString(){
        long mills = Calendar.getInstance().getTimeInMillis()-date.getTimeInMillis();
        if(mills < 60000)//less than 1 min
            return new String("Just now");
        else if(mills < 120000){//between 1 min and 2 mins
            StringBuffer sb=new StringBuffer();
            sb.append(mills/60000);
            sb.append(" min ago");
            return new String(sb);
        }
        else if(mills < 3600000){//between 2 mins and 1 hour
            StringBuffer sb=new StringBuffer();
            sb.append(mills/60000);
            sb.append(" mins ago");
            return new String(sb);
        }
        else if(mills < 7200000){//between 1 hour and 2 hours
            StringBuffer sb=new StringBuffer();
            sb.append(mills/60000);
            sb.append(" hour ago");
            return new String(sb);
        }
        else if(mills < 86400000){//between 2 hours and 1 day
            StringBuffer sb=new StringBuffer();
            sb.append(mills/3600000);
            sb.append(" hours ago");
            return new String(sb);
        }
        else if(mills < 172800000){//between 1 day and 2 days
            StringBuffer sb=new StringBuffer();
            sb.append(mills/3600000);
            sb.append(" day ago");
            return new String(sb);
        }
        else {//more than 1 day
            StringBuffer sb = new StringBuffer();
            sb.append(mills / 86400000);
            sb.append(" days ago");
            return new String(sb);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
