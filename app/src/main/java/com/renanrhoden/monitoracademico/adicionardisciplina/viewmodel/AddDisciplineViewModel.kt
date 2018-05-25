package com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.hypertrack.smart_scheduler.SmartScheduler

class AddDisciplineViewModel {
    val mNameDiscipline = ObservableField<String>("")
    val mSegundaChecked = ObservableBoolean(false)
    val mTercaChecked = ObservableBoolean(false)
    val mQuartaChecked = ObservableBoolean(false)
    val mQuintaChecked = ObservableBoolean(false)
    val mSextaChecked = ObservableBoolean(false)
    val mSabadoChecked = ObservableBoolean(false)
    val mDomingoChecked = ObservableBoolean(false)

    val callback = SmartScheduler.JobScheduledCallback { context, job ->

    }
//
//    fun createJob(){
//        val builder = Job.Builder(1, callback, Job.Type.JOB_TYPE_ALARM, "tag")
//                .
//    }
//    .setRequiredNetworkType(networkType)
//    .setRequiresCharging(requiresCharging)
//    .setIntervalMillis(intervalInMillis);
//
//    if (isPeriodic) {
//        builder.setPeriodic(intervalInMillis);
//    }
//
//    Job job = builder.build();
}