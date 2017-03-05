package liusuwan.androiddemo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

public class NotifyActivity extends AppCompatActivity {

    @BindView(R.id.btn_notify)
    Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showNotification(NotifyActivity.this, 0, "hello", "world");
                showCustomNotify();
            }
        });
    }

    private void showNotification(Context context, int id, String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_unity);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources()
                , R.drawable.ic_unity));
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    public void showCustomNotify() {
        RemoteViews headsUpView = new RemoteViews(getPackageName(), R.layout.notify_custom);

        Intent intent = new Intent(NotifyActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotifyActivity.this,
                1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotifyActivity.this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("hello")
                .setContentText("hello")
                .setNumber((int) (Math.random() * 1000))
                .setTicker("test")
                //must set pendingintent for this notification, or will be crash
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND
                        | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                .setAutoCancel(true)
                .setWhen(0);
        Notification notification = mBuilder.build();
        if (Build.VERSION.SDK_INT >= 21) {
            notification.priority = Notification.PRIORITY_MAX;
            notification.headsUpContentView = headsUpView;
        }
        NotificationManager notificationManager = (NotificationManager) NotifyActivity.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(12, notification);
    }
}
