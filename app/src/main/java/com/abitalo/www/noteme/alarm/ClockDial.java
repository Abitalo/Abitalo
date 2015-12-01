package com.abitalo.www.noteme.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.abitalo.www.noteme.R;

/**
 * Created by asus on 2015/11/26.
 */
public class ClockDial extends View {
    //时钟盘
    Bitmap mBmpDial;
    BitmapDrawable bmdDial;

    int mWidth;
    int mHeigh;
    int centerX;
    int centerY;

    int availableWidth = 220;
    int availableHeight = 220;

    public ClockDial(Context context, AttributeSet attr) {
        super(context,attr);

        mBmpDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.clock);
        bmdDial = new BitmapDrawable(context.getResources(),mBmpDial);

        mWidth = mBmpDial.getWidth();
        mHeigh = mBmpDial.getHeight();

        DisplayMetrics dm;
        dm = getResources().getDisplayMetrics();

        availableWidth =  (int) (260 * dm.density);
        availableHeight =  (int) (260 * dm.density);

        centerX = (int) (130 * dm.density);
        centerY = (int) (130 * dm.density);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = Math.min((float) availableWidth / (float) mWidth,
                (float) availableHeight / (float) mHeigh);
        canvas.save();
        canvas.scale(scale, scale, centerX, centerY);

        bmdDial.setBounds(centerX - (mWidth / 2), centerY - (mHeigh / 2),
                centerX + (mWidth / 2), centerY + (mHeigh / 2));
        bmdDial.draw(canvas);

    }
}
