package com.renanrhoden.monitoracademico.scheduler

import android.app.IntentService
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION

class AlertService : IntentService("AlertService") {
    override fun onHandleIntent(intent: Intent?) {
            val intent2 = Intent(this, AlertDialogActivity::class.java)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent2)

    }
}