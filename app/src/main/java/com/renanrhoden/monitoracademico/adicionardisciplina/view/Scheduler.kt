package com.renanrhoden.monitoracademico.adicionardisciplina.view

interface Scheduler {
    fun scheduleAlarm(dayOfWeek: Int, discipline: String, hour: Int)
}