package org.example.kadai06;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//FIXME 指定秒数分の待機時間設定

public class MyService extends Service {
    private final String TAG = "MyService";
    private static final int NOTIFY_ID = 0;
    private NotificationManager manager;
    private NotificationChannel channel;
    private ScheduledExecutorService schedule;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        //LogにServiceの動作を記録
        Log.i(TAG, "onCreate");

        //POP通知用のチャンネル生成
        channel = new NotificationChannel(
                "service_status", "ServiceStatus",
                NotificationManager.IMPORTANCE_DEFAULT
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Notificationを定義
        Notification notif = new NotificationCompat.Builder(this, "service_status")
                .setContentTitle("MyService")
                .setContentText("サービスは起動中です")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(
                        PendingIntent.getActivity(this, MainActivity.ACTIVITY_ID,
                                new Intent(this, MainActivity.class),
                                PendingIntent.FLAG_CANCEL_CURRENT)
                )
                .build();
        //Notificationを登録
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(NOTIFY_ID, notif);

        schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                Log.i(TAG, "onStartCommand");
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.cancel(NOTIFY_ID);
        Log.i(TAG, "onDestroy");
        schedule.shutdown();
    }
}