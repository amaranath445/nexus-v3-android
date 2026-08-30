package com.nexus.v3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;
import android.app.NotificationManager;
import android.app.Notification;
import androidx.core.app.NotificationCompat;

public class JavaScriptBridge {

    private Context context;
    private Vibrator vibrator;
    private static final String CHANNEL_ID = "nexus_notifications";

    public JavaScriptBridge(Context context, Vibrator vibrator) {
        this.context = context;
        this.vibrator = vibrator;
    }

    @JavascriptInterface
    public void vibrate(long duration) {
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(android.os.VibrationEffect.createOneShot(
                        duration,
                        android.os.VibrationEffect.DEFAULT_AMPLITUDE
                ));
            } else {
                vibrator.vibrate(duration);
            }
        }
    }

    @JavascriptInterface
    public void postNotification(String title, String message) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }

    @JavascriptInterface
    public boolean getOnlineStatus() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    @JavascriptInterface
    public String getDeviceInfo() {
        return "Android " + Build.VERSION.RELEASE + " (SDK " + Build.VERSION.SDK_INT + ")";
    }
}
