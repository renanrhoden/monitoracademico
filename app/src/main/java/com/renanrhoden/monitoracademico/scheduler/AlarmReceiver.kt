package com.renanrhoden.monitoracademico.scheduler

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var componentName: ComponentName? = ComponentName(context?.packageName, AlertService::class.java.name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val extras = intent?.extras
            val foreIntent = Intent(context, AlertService::class.java)
            foreIntent.putExtras(extras)
            componentName = context?.startForegroundService(foreIntent)
        }
        startWakefulService(context, (intent?.setComponent(componentName)))
    }
}