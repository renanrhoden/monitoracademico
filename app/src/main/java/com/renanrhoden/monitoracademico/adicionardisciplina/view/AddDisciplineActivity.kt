package com.renanrhoden.monitoracademico.adicionardisciplina.view

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel.AddDisciplineViewModel
import com.renanrhoden.monitoracademico.databinding.AddDisciplineActivityBinding
import com.renanrhoden.monitoracademico.main.view.MainActivity
import com.renanrhoden.monitoracademico.scheduler.AlarmReceiver
import kotlinx.android.synthetic.main.add_discipline_activity.*
import android.widget.Toast
import android.app.AlarmManager
import com.renanrhoden.monitoracademico.R.id.save
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
            triggerAlarmManager(2)
        }
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
}