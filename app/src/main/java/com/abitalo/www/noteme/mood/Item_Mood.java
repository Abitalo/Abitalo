package com.abitalo.www.noteme.mood;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by asus on 2015/11/25.
 */
public class Item_Mood {
    private static SimpleDateFormat form=null;
    private Calendar date=null;
    private String text=null;

    public Item_Mood(Calendar date,String text) {
        this.date=date;
        this.text=text;
    }

    public Item_Mood(String date, String text) {
        form=new SimpleDateFormat("yyyyMMdd");
        this.date = Calendar.getInstance();
        try{
            this.date.setTime(form.parse(date));
        }catch(ParseException e){
            Log.i("exception","error in parse date/init");
        }
        this.text = text;
    }

    public String getDateString() {
        form=new SimpleDateFormat("yyyy.MM.dd");
        return form.format(date.getTime());
    }

    public Calendar getDate(){
        return date;
    }

    public String getText() {
        return text;
    }
}

