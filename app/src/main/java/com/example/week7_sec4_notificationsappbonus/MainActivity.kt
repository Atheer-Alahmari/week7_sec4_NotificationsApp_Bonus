package com.example.week7_sec4_notificationsappbonus

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder: Notification.Builder
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var countBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countBtn = findViewById(R.id.count_btn)


        countBtn.setOnClickListener {

            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, NotificationActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.coffee)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Coffee Preparation Timer")
                    .setContentText("Ready")

            } else {
                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.coffee)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Coffee Preparation Timer")
                    .setContentText(("Ready"))

            }

            object : CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    notificationManager.notify(1234, builder.build())

                }
            }.start()
        }
    }


}