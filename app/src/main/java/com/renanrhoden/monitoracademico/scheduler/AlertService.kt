package com.renanrhoden.monitoracademico.scheduler

import android.app.IntentService
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import com.renanrhoden.monitoracademico.DISCIPLINE

class AlertService : IntentService("AlertService") {
    override fun onHandleIntent(incomingIntent: Intent?) {
        val alertActivity = Intent(this, AlertDialogActivity::class.java)
        val disciplina = incomingIntent?.getStringExtra(DISCIPLINE)
        alertActivity.putExtra(DISCIPLINE, disciplina)
        alertActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(alertActivity)
    }
}