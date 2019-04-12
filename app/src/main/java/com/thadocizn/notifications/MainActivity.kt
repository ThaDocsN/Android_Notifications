package com.thadocizn.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    internal var notificationManager: NotificationManager
    internal var context: Context
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        findViewById(R.id.btnNotification).setOnClickListener(View.OnClickListener {
            val channelID = getPackageName() + ".button"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Demo Button"
                val description = "Notification"
                val importance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel(channelID, name, importance)
                channel.description = description

                notificationManager.createNotificationChannel(channel)
            }

            val buttonPressed = " button was pressed"

            val intent = Intent(context, FullscreenActivity::class.java)
            intent.putExtra(BUTTON_PRESSED, buttonPressed)
            val intent1 = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            val builder = NotificationCompat.Builder(context, channelID)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setContentTitle(channelID)
                    .setContentIntent(intent1)
                    .setContentText("The button was pressed")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setColor(context.resources.getColor(R.color.colorPrimaryDark))
                    .setDefaults(Notification.DEFAULT_ALL)


            notificationManager.notify(1, builder.build())

            /*String buttonPressed = "The button was pressed";

                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra(BUTTON_PRESSED, buttonPressed);
                PendingIntent intent1 = PendingIntent.getActivity(context,0, intent, PendingIntent.FLAG_ONE_SHOT);*/
        })
    }

    companion object {

        val BUTTON_PRESSED = "button_pressed"
    }
}
