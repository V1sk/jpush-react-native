package cn.jpush.reactnativejpush;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * Created by chenjianwei on 2018/2/25.
 */

public class NotificationUtil extends ContextWrapper {


    private NotificationManager manager;
    public static final String CHANNEL_1 = "channel_1";
    public static final String name = "channel_name_1";

    public NotificationUtil(Context context) {
        super(context);
    }


    @TargetApi(Build.VERSION_CODES.O)
    public void showChannelNotification(String title, String content, PendingIntent pendingIntent, int id) {
        Notification.Builder builder = new Notification.Builder(getApplicationContext(), CHANNEL_1);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(IdHelper.getDrawable(getApplicationContext(), "ic_launcher"));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager notifyManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (notifyManager != null)
            notifyManager.notify(id, notification);
    }

    public void showNotification_25(String title, String content, PendingIntent pendingIntent, int id) {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(IdHelper.getDrawable(getApplicationContext(), "ic_launcher"));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager notifyManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (notifyManager != null)
            notifyManager.notify(id, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_1, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public void sendNotification(String title, String content, PendingIntent pendingIntent, int id) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            showChannelNotification(title, content, pendingIntent, id);
        } else {
            showNotification_25(title, content, pendingIntent, id);
        }
    }
}
