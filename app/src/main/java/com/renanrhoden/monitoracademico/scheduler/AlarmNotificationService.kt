package com.renanrhoden.monitoracademico.scheduler

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.main.view.MainActivity


class AlarmNotificationService : IntentService("AlarmNotificationService") {
    private var alarmNotificationManager: NotificationManager? = null

    public override fun onHandleIntent(intent: Intent?) {

        //Send notification
        sendNotification("Wake Up! Wake Up! Alarm started!!")
    }

    //handle notification
    private fun sendNotification(msg: String) {
        alarmNotificationManager = this
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //get pending intent
        val contentIntent = PendingIntent.getActivity(this, 0,
                Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        //Create notification
        val alamNotificationBuilder = NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setAutoCancel(true)
        alamNotificationBuilder.setContentIntent(contentIntent)

        //notiy notification manager about new notification
        alarmNotificationManager!!.notify(NOTIFICATION_ID, alamNotificationBuilder.build())
    }

    companion object {

        //Notification ID for Alarm
        val NOTIFICATION_ID = 1
    }


}