package io.github.sudhanshu2.appathon2018;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

public class AuxConnService extends Service {

    private BroadcastReceiver broadcastReceiver;

    private NotificationManager notificationManager;

    {
        broadcastReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if (bundle == null) {
                    return;
                }
                Integer state = (Integer) bundle.get("state");
                if (state == null) {
                    return;
                }

                if (state == 1) {
                    showNotification(context);
                }

            }
        };
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showNotification(Context context) {
        notificationManager = getSystemService(NotificationManager.class);
        Notification.Builder builder = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("io.gitub.sudhanshu2.appathon2018", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(context, channel.getId());
        } else {
            builder = new Notification.Builder(context);
        }

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1,
                intent, 0);

//        Intent YesIntent = new Intent(context, YesActivity.class);
//        YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent YesPendingIntent = PendingIntent.getActivity(context, 0, YesIntent, PendingIntent.FLAG_ONE_SHOT);
//
//        Intent NoIntent = new Intent(context, NoActivity.class);
//        NoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent NoPendingIntent = PendingIntent.getActivity(context, 0, NoIntent, PendingIntent.FLAG_ONE_SHOT);


        builder.setOngoing(true)
                .setContentIntent(pendingIntent)
                .setContentTitle("Headphones detected:")
                .setContentText("Service enabled")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setDefaults(Notification.DEFAULT_ALL);
        //  .addAction(R.drawable.ic_sms_notification, "Yes", YesPendingIntent)
        //  .addAction(R.drawable.ic_sms_notification, "No", NoPendingIntent);

        notificationManager.notify(0, builder.build());

    }

    private IntentFilter intentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        this.registerReceiver(broadcastReceiver, intentFilter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
