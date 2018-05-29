package com.renanrhoden.monitoracademico.adicionardisciplina.view

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel.AddDisciplineViewModel
import com.renanrhoden.monitoracademico.databinding.AddDisciplineActivityBinding
import com.renanrhoden.monitoracademico.main.view.MainActivity
import com.renanrhoden.monitoracademico.scheduler.AlarmReceiver
import kotlinx.android.synthetic.main.add_discipline_activity.*
import java.util.*


class AddDisciplineActivity : AppCompatActivity() {
    lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<AddDisciplineActivityBinding>(this, R.layout.add_discipline_activity)
        binding.viewModel = AddDisciplineViewModel()

        val alarmManager = getSystemService(Context.ALARM_SERVICE)

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, MainActivity.ALARM_REQUEST_CODE, intent, 0)

        save.setOnClickListener {
            triggerAlarmManager(5)
        }

        val builder = AlertDialog.Builder(this)
        val simListener = DialogInterface.OnClickListener { dialog, which -> }
        val naoListener = DialogInterface.OnClickListener { dialog, which -> }

        builder.setTitle("Foi na aula?")
                .setMessage("Foi na aula de IHC?")
                .setPositiveButton("SIM", simListener)
                .setNegativeButton("NAO", naoListener)

        builder.create().show()
    }

    fun triggerAlarmManager(alarmTriggerTime: Int) {
        // get a Calendar object with current time
        val cal = Calendar.getInstance()
        // add alarmTriggerTime seconds to the calendar object
        cal.add(Calendar.SECOND, alarmTriggerTime)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager//get instance of alarm manager
        manager.set(AlarmManager.RTC, cal.timeInMillis, pendingIntent)//set alarm manager with entered timer by converting into milliseconds

        Toast.makeText(this, "Alarm Set for $alarmTriggerTime seconds.", Toast.LENGTH_SHORT).show()
    }

    private fun scheduleAlarm(dayOfWeek: Int) {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        calendar.set(Calendar.HOUR_OF_DAY, 19)

        // Check we aren't setting it in the past which would trigger it to fire instantly
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
        }

        // Set this to whatever you were planning to do at the given time
        val yourIntent = pendingIntent

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, yourIntent)
    }

    private fun setUpAlarms() {

        scheduleAlarm(Calendar.MONDAY)
        scheduleAlarm(Calendar.FRIDAY)
    }
}