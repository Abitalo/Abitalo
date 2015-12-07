package com.abitalo.www.noteme.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.abitalo.www.noteme.R;

import java.util.Calendar;

/**
 * Created by asus on 2015/11/26.
 */
public class Clock extends View {

    //时钟分针、时针对象
    Bitmap mBmpDial;
    Bitmap mBmpHour;
    Bitmap mBmpMinute;
    Bitmap mBmpBlueHour;
    Bitmap mBmpBlueMinute;

    BitmapDrawable bmdHour;
    BitmapDrawable bmdMinute;
//    BitmapDrawable bmdDial;
    BitmapDrawable bmdBlueHour;
    BitmapDrawable bmdBlueMinute;

    int mWidth;
    int mHeigh;
    int mTempWidth;
    int mTempHeigh;
    int centerX;
    int centerY;

    int availableWidth = 220;
    int availableHeight = 220;

    int eventHour = 21;
    int eventMinute = 2;

    Calendar eventStartTime;

    public Clock(Context context, AttributeSet attr) {
        super(context,attr);

        eventStartTime = Calendar.getInstance();

        mBmpHour = BitmapFactory.decodeResource(getResources(),
                R.drawable.hour_current);
        bmdHour = new BitmapDrawable(context.getResources(),mBmpHour);

        mBmpMinute = BitmapFactory.decodeResource(getResources(),
                R.drawable.min_current);
        bmdMinute = new BitmapDrawable(context.getResources(),mBmpMinute);

        mBmpDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.clock);
//        bmdDial = new BitmapDrawable(context.getResources(),mBmpDial);

        mBmpBlueHour = BitmapFactory.decodeResource(getResources(),
                R.drawable.hour_des);
        bmdBlueHour = new BitmapDrawable(context.getResources(),mBmpBlueHour);

        mBmpBlueMinute = BitmapFactory.decodeResource(getResources(),
                R.drawable.min_des);
        bmdBlueMinute = new BitmapDrawable(context.getResources(),mBmpBlueMinute);

        mWidth = mBmpDial.getWidth();
        mHeigh = mBmpDial.getHeight();

        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        int screenWidth  = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）

        availableWidth =  (int) (260 * dm.density);
        availableHeight =  (int) (260 * dm.density);

        centerX = (int) (130 * dm.density);
        centerY = (int) (130 * dm.density);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        float hourRotate = hour * 30.0f + minute / 60.0f * 30.0f;
        float minuteRotate = minute * 6.0f;
        float secondRotate = second * 6.0f;

        float scale = Math.min((float) availableWidth / (float) mWidth,
                (float) availableHeight / (float) mHeigh);
        canvas.save();
        canvas.scale(scale, scale, centerX, centerY);

        mTempWidth = bmdHour.getIntrinsicWidth();
        mTempHeigh = bmdHour.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(hourRotate, centerX, centerY);
        bmdHour.setBounds(centerX - (mTempWidth / 2), centerY
                - (mTempHeigh / 2), centerX + (mTempWidth / 2), centerY
                + (mTempHeigh / 2));
        bmdHour.draw(canvas);

        canvas.restore();

        mTempWidth = bmdMinute.getIntrinsicWidth();
        mTempHeigh = bmdMinute.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(minuteRotate, centerX, centerY);
        bmdMinute.setBounds(centerX - (mTempWidth / 2), centerY
                - (mTempHeigh / 2), centerX + (mTempWidth / 2), centerY
                + (mTempHeigh / 2));
        bmdMinute.draw(canvas);

        canvas.restore();

        hourRotate = eventHour * 30.0f + eventMinute / 60.0f * 30.0f;
        minuteRotate = eventMinute * 6.0f;

        mTempWidth = bmdBlueHour.getIntrinsicWidth();
        mTempHeigh = bmdBlueHour.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(hourRotate, centerX, centerY);
        bmdBlueHour.setBounds(centerX - (mTempWidth / 2), centerY
                - (mTempHeigh / 2), centerX + (mTempWidth / 2), centerY
                + (mTempHeigh / 2));
        bmdBlueHour.draw(canvas);

        canvas.restore();

        mTempWidth = bmdBlueMinute.getIntrinsicWidth();
        mTempHeigh = bmdBlueMinute.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(minuteRotate, centerX, centerY);
        bmdBlueMinute.setBounds(centerX - (mTempWidth / 2), centerY
                - (mTempHeigh / 2), centerX + (mTempWidth / 2), centerY
                + (mTempHeigh / 2));
        bmdBlueMinute.draw(canvas);

        canvas.restore();

    }

    public void setEventTime(Item_Alarm itemAlarm){
        eventStartTime = itemAlarm.getStartTime();
        eventHour=Integer.parseInt(itemAlarm.getStartTimeHour());
        eventMinute=Integer.parseInt(itemAlarm.getStartTimeMinute());
        postInvalidate();
    }
}
