package com.abitalo.www.noteme;

/**
 * Created by asus on 2015/11/25.
 */
public class DateText {
    private String date;
    private String text;

    public DateText() {

    }

    public DateText(String date, String text) {
        super();
        this.date = date;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

