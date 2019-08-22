package com.ahmadfauzirahman.sakato;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.ahmadfauzirahman.sakato.utils.SessionManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FCMService extends FirebaseMessagingService {
    SessionManager sessionManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("LOg", remoteMessage.toString());
        notif(remoteMessage.getData().get("message"), remoteMessage.getData().get("status"));
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("Token Log", s);
        sessionManager = new SessionManager(this);
        sessionManager.saveToken(s);
    }

    public void notif(String title, String Content) {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getBaseContext(), "channel1");
        notification.setContentTitle(title)
                .setSmallIcon(R.drawable.ceria)
                .setContentText(Content).setPriority(NotificationCompat.PRIORITY_HIGH).build();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotifcationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("channel1", "NOTIFICATION_CHANNEL_SAKATO", NotificationManager.IMPORTANCE_HIGH);
            notification.setSmallIcon(R.drawable.ceria);
            mNotifcationManager.createNotificationChannel(notificationChannel);
        }

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(m, notification.build());
    }


}
