package com.codewithcoffee.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_Show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_Show = findViewById(R.id.bt_Show);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NotificationChannel channel = new NotificationChannel("my Notification", "id"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        bt_Show.setOnClickListener(view -> notifyUser("Lorem ipsum dolor sit amet" +
                " consectetur adipisicing elit. Fuga quis " +
                "facilis delectus eius est asperiores laudantium " +
                "doloremque quasi neque, dolore aut, nobis cumque" +
                " totam deleniti. Necessitatibus, odit sint! " +
                " est pariatur aliquid?"));

    }
    private void notifyUser(String message)
    {
      Drawable drawable = ResourcesCompat.getDrawable(getResources(),
              R.drawable.large,null);
      BitmapDrawable bd = (BitmapDrawable) drawable;
      Bitmap largeIcon = bd.getBitmap();

        Intent intent = new Intent(this,NextActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"channel")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Message")
                        .setLargeIcon(largeIcon)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }
}