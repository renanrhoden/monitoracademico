package com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View.GONE
import android.view.View.VISIBLE
import com.renanrhoden.monitoracademico.adicionardisciplina.view.Scheduler
import java.util.Calendar.*

class AddDisciplineViewModel {
    val mNameDiscipline = ObservableField<String>("")
    val mSegundaChecked = ObservableBoolean(false)
    val mTercaChecked = ObservableBoolean(false)
    val mQuartaChecked = ObservableBoolean(false)
    val mQuintaChecked = ObservableBoolean(false)
    val mSextaChecked = ObservableBoolean(false)
    val mSabadoChecked = ObservableBoolean(false)
    val mDomingoChecked = ObservableBoolean(false)
    val mManhaChecked = ObservableBoolean(false)
    val mTardeChecked = ObservableBoolean(false)
    val mNoiteChecked = ObservableBoolean(false)
    val mErrorVisibility = ObservableInt(GONE)
    lateinit var scheduler: Scheduler

    fun onSalvarClick() {
        mErrorVisibility.set(GONE)
        val period = getPeriodOfDay()
        if (mNameDiscipline.get() == "") {
            mErrorVisibility.set(VISIBLE)
            return
        }

        if (mSegundaChecked.get()) scheduler.scheduleAlarm(
                MONDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mTercaChecked.get()) scheduler.scheduleAlarm(
                TUESDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mQuartaChecked.get()) scheduler.scheduleAlarm(
                WEDNESDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mQuintaChecked.get()) scheduler.scheduleAlarm(
                THURSDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mSextaChecked.get()) scheduler.scheduleAlarm(
                FRIDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mSabadoChecked.get()) scheduler.scheduleAlarm(
                SATURDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        if (mDomingoChecked.get()) scheduler.scheduleAlarm(
                SUNDAY,
                mNameDiscipline.get() ?: "",
                period
        )
        mErrorVisibility.notifyChange()
    }

    private fun getPeriodOfDay(): Int {
        return when {
            mManhaChecked.get() -> 13
            mManhaChecked.get() -> 19
            mNoiteChecked.get() -> 23
            else -> {
                0
            }
        }
    }

}