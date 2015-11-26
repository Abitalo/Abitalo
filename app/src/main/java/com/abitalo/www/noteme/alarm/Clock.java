package com.abitalo.www.noteme.alarm;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
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

    BitmapDrawable bmdHour;
    BitmapDrawable bmdMinute;
    BitmapDrawable bmdDial;

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

    private String sTimeZoneString;


    public Clock(Context context,AttributeSet attr)
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
                R.drawable.rline);
        bmdMinute = new BitmapDrawable(context.getResources(),mBmpMinute);

        mBmpDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.clock);
        bmdDial = new BitmapDrawable(context.getResources(),mBmpDial);
        mWidth = mBmpDial.getWidth();
        mHeigh = mBmpDial.getHeight();

        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        int screenWidth  = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）

        availableWidth = screenWidth / 2;
        availableHeight = availableWidth;
        centerX = (int) (110*dm.density);
        centerY = availableHeight / 2;

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        run();
    }

    public void run() {
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
        float hourRotate = hour * 30.0f + minute / 60.0f * 30.0f;
        float minuteRotate = minute * 6.0f;

        boolean scaled = false;

        if (availableWidth < mWidth || availableHeight < mHeigh) {
            scaled = true;
            float scale = Math.min((float) availableWidth / (float) mWidth,
                    (float) availableHeight / (float) mHeigh);
            canvas.save();
            canvas.scale(1.0f, scale, centerX, centerY);
        }

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

        if (scaled) {
            canvas.restore();
        }
    }
}
