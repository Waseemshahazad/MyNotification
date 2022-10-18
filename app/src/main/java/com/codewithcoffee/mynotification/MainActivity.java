package com.codewithcoffee.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
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


        bt_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    notifyUser("Lorem ipsum dolor sit amet" +
                            " consectetur adipisicing elit. Fuga quis " +
                            "facilis delectus eius est asperiores laudantium " +
                            "doloremque quasi neque, dolore aut, nobis cumque" +
                            " totam deleniti. Necessitatibus, odit sint! " +
                            " est pariatur aliquid?");
            }
        });

    }
    private void notifyUser(String message)
    {
      Drawable drawable = ResourcesCompat.getDrawable(getResources(),
              R.drawable.large,null);
      BitmapDrawable bd = (BitmapDrawable) drawable;
      Bitmap largeIcon = bd.getBitmap();


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"channel")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Message")
                        .setLargeIcon(largeIcon)
                        .setContentText(message)
                        .setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }
}