package com.abitalo.www.noteme.alarm;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;

/**
 * Created by Lancelot on 2015/12/9.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String note=intent.getStringExtra("note");
        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_ab_drawer);
        builder.setTicker("Noteme");
        builder.setContentTitle("It's time for");
        builder.setContentText(note);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_ALL);
        Intent intentTarget = new Intent(context, Main.class);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intentTarget, PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setAutoCancel(true);

        Notification notification=builder.build();
        nm.notify(0,notification);
    }
}
