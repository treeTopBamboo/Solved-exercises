package org.example.kadai06;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    private final String TAG = "MyService";
    private static final int NOTIFY_ID = 0;
    private NotificationManager manager;
    private NotificationChannel channel;
    private ScheduledExecutorService schedule;

    @SuppressLint("NewApi")
    @Override
    public void onCreate() {
        super.onCreate();

        //POP通知用のチャンネル生成
        channel = new NotificationChannel(
                "service_status", "ServiceStatus",
                NotificationManager.IMPORTANCE_DEFAULT
        );
    }

    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Notificationを定義（POP通知に表示させる内容）
        Notification notif = new NotificationCompat.Builder(this, "service_status")
                //通知のタイトル
                .setContentTitle("MyService")
                //通知に表示させるメッセージ
                .setContentText(getString(R.string.ss_text))
                //通知の表示アイコン
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                //通知時刻
                .setWhen(System.currentTimeMillis())
                //通知タップ時にアプリを表示する
                .setContentIntent(
                        PendingIntent.getActivity(this, MainActivity.ACTIVITY_ID,
                                new Intent(this, MainActivity.class),
                                PendingIntent.FLAG_CANCEL_CURRENT)
                )
                .build();
        //定義したNotificationを登録
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(NOTIFY_ID, notif);

        //Notification起動
        schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onStartCommand");
            }
        //同数にすると指定時間にアプリが落ちる
        }, 0, 5000, TimeUnit.MILLISECONDS);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        manager.cancel(NOTIFY_ID);
        schedule.shutdown();
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}