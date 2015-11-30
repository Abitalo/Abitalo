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
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.abitalo.www.noteme.R;
import com.abitalo.www.noteme.Varible;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by asus on 2015/11/26.
 */
public class Clock extends View {
    //时钟上时间文字的大小（dp)
    int SP_VALUE = 16;

    //文字颜色Red
    String RED_TEXT="#f57b7b";
    //文字颜色Bule
    String BULE_TEXT="#6ddec8";
    //文字颜色Gray
    String GRAY_TEXT="#d3caba";

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

    Calendar eventStartTime;
    Calendar eventTerminalTime;


    public Clock(Context context, AttributeSet attr) {
        super(context,attr);

        eventStartTime = Calendar.getInstance();
        eventTerminalTime = Calendar.getInstance();

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
        String remainingTime = "剩余：00:00";
        TextPaint mTextPaint = new TextPaint();

        float scale = Math.min((float) availableWidth / (float) mWidth,
                (float) availableHeight / (float) mHeigh);
        canvas.save();
        canvas.scale(scale, scale, centerX, centerY);

        bmdDial.setBounds(centerX - (mWidth / 2), centerY - (mHeigh / 2),
                centerX + (mWidth / 2), centerY + (mHeigh / 2));
        bmdDial.draw(canvas);
        //换算单位
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        int pixel = (int)(SP_VALUE * fontScale + 0.5f);
        mTextPaint.setTextSize(pixel);

        //比较时间
        //1.未开始统计还有多少时间
        if(eventHour > cal.get(Calendar.HOUR_OF_DAY) ||
                ((eventHour == cal.get(Calendar.HOUR_OF_DAY)) && eventMinute > cal.get(Calendar.MINUTE))){
            mTextPaint.setColor(Color.parseColor(BULE_TEXT));
            long diff = eventHour * 60 + eventMinute - (cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE));
            long hh = diff / 60;
            long mm = diff - hh * 60;
            remainingTime = "还有：" + String.format("%02d",hh) + ":" + String.format("%02d",mm);
        }
        //2.已经开始统计剩下多少时间
        else if(eventTerminalTime.get(Calendar.HOUR_OF_DAY) > cal.get(Calendar.HOUR_OF_DAY) ||
                (eventTerminalTime.get(Calendar.HOUR_OF_DAY) == cal.get(Calendar.HOUR_OF_DAY) &&
                        eventTerminalTime.get(Calendar.MINUTE) >= cal.get(Calendar.MINUTE))){
            mTextPaint.setColor(Color.parseColor(RED_TEXT));
            long diff = (eventTerminalTime.get(Calendar.HOUR_OF_DAY) * 60 + eventTerminalTime.get(Calendar.MINUTE) - (cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE)));
            long hh = diff / 60;
            long mm = diff - hh * 60;
            remainingTime = "剩余：" + String.format("%02d",hh) + ":" + String.format("%02d",mm);
        }
        else{
            mTextPaint.setColor(Color.parseColor(GRAY_TEXT));
            remainingTime = "结束：00:00";
        }

        // 计算Baseline绘制的起点X轴坐标 ，计算方式：画布宽度的1/2 - 文字宽度的1/2
        int baseX = (int) (canvas.getWidth() / 2 - mTextPaint.measureText(remainingTime) / 2);

        // 计算Baseline绘制的Y坐标 ，计算方式：画布高度的4/5 - 文字总高度的1/2
        int baseY = (int) (4 * (canvas.getHeight() / 5) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2));

        // 画文字
        canvas.save();
        canvas.scale(1 / scale, 1 / scale, centerX, centerY);
        canvas.drawText(remainingTime, baseX, baseY, mTextPaint);
        canvas .restore();

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

    public void setEventTime(Item_Alarm itemAlarm){
        eventStartTime = itemAlarm.getStartTime();
        eventTerminalTime = itemAlarm.getTerminalTime();
        eventHour=Integer.parseInt(itemAlarm.getStartTimeHour());
        eventMinute=Integer.parseInt(itemAlarm.getStartTimeMinute());
        postInvalidate();
    }

}
