package com.renanrhoden.monitoracademico.scheduler

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver.startWakefulService

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val componentName = ComponentName(context?.packageName, AlarmNotificationService::class.java.name)
        startWakefulService(context, (intent?.setComponent(componentName)))
    }
}