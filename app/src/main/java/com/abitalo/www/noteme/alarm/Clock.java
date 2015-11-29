package com.abitalo.www.noteme.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.abitalo.www.noteme.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by asus on 2015/11/26.
 */
public class Clock extends View {
    //时钟盘，分针、秒针、时针对象

    Bitmap mBmpDial;
    Bitmap mBmpHour;
    Bitmap mBmpMinute;
    Bitmap mBmpSecond;
    Bitmap mBmpBlueHour;
    Bitmap mBmpBlueMinute;

    BitmapDrawable bmdHour;
    BitmapDrawable bmdMinute;
    BitmapDrawable bmdDial;
    BitmapDrawable bmdSecond;
    BitmapDrawable bmdBlueHour;
    BitmapDrawable bmdBlueMinute;

    Paint mPaint;

    Handler tickHandler;

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

    private String sTimeZoneString;


    public Clock(Context context, AttributeSet attr)
    {
        this(context,"GMT+8：00");

    }

    public Clock(Context context, String sTime_Zone) {
        super(context);
        sTimeZoneString = sTime_Zone;

        mBmpHour = BitmapFactory.decodeResource(getResources(),
                R.drawable.rline);
        bmdHour = new BitmapDrawable(context.getResources(),mBmpHour);

        mBmpMinute = BitmapFactory.decodeResource(getResources(),
                R.drawable.rmin);
        bmdMinute = new BitmapDrawable(context.getResources(),mBmpMinute);

        mBmpSecond = BitmapFactory.decodeResource(getResources(),
                R.drawable.rsecond);
        bmdSecond = new BitmapDrawable(getResources(),mBmpSecond);

        mBmpDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.clock);
        bmdDial = new BitmapDrawable(context.getResources(),mBmpDial);

        mBmpBlueHour = BitmapFactory.decodeResource(getResources(),
                R.drawable.bline);
        bmdBlueHour = new BitmapDrawable(context.getResources(),mBmpBlueHour);

        mBmpBlueMinute = BitmapFactory.decodeResource(getResources(),
                R.drawable.bmin);
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

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        run();
    }

    protected void run() {
        tickHandler = new Handler();
        tickHandler.post(tickRunnable);
    }

    private Runnable tickRunnable = new Runnable() {
        public void run() {
            postInvalidate();
            tickHandler.postDelayed(tickRunnable, 1000);
        }
    };

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Calendar cal = Calendar.getInstance(TimeZone
                .getTimeZone(sTimeZoneString));
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

        bmdDial.setBounds(centerX - (mWidth / 2), centerY - (mHeigh / 2),
                centerX + (mWidth / 2), centerY + (mHeigh / 2));
        bmdDial.draw(canvas);

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

        mTempWidth = bmdSecond.getIntrinsicWidth();
        mTempHeigh = bmdSecond.getIntrinsicHeight();
        canvas.save();
        canvas.rotate(secondRotate, centerX, centerY);
        bmdSecond.setBounds(centerX - (mTempWidth / 2), centerY
                - (mTempHeigh / 2), centerX + (mTempWidth / 2), centerY
                + (mTempHeigh / 2));
        bmdSecond.draw(canvas);

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

    public void setEventTime(int hour ,int minute){
        eventHour=hour;
        eventMinute=minute;
    }
}
