package com.renanrhoden.monitoracademico.scheduler

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.content.WakefulBroadcastReceiver.startWakefulService

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         var componentName: ComponentName? = ComponentName(context?.packageName, AlertService::class.java.name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            componentName = context?.startForegroundService(Intent(context, AlertService::class.java))
        }
        startWakefulService(context, (intent?.setComponent(componentName)))
    }
}