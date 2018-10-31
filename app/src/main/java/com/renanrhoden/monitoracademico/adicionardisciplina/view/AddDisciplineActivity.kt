package com.renanrhoden.monitoracademico.adicionardisciplina.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.renanrhoden.monitoracademico.DISCIPLINE
import com.renanrhoden.monitoracademico.PREFERENCIA
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel.AddDisciplineViewModel
import com.renanrhoden.monitoracademico.databinding.AddDisciplineActivityBinding
import com.renanrhoden.monitoracademico.main.view.MainActivity
import com.renanrhoden.monitoracademico.scheduler.AlarmReceiver
import java.util.*


class AddDisciplineActivity : AppCompatActivity(), Scheduler {
    lateinit var viewModel: AddDisciplineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<AddDisciplineActivityBinding>(this, R.layout.add_discipline_activity)
        viewModel = AddDisciplineViewModel()
        viewModel.scheduler = this
        binding.viewModel = viewModel

    }

    override fun scheduleAlarm(dayOfWeek: Int, discipline: String, hour: Int) {

        val shared = getSharedPreferences(PREFERENCIA, MODE_PRIVATE)
        shared.edit().putInt(discipline, 0).apply()
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra(DISCIPLINE, discipline)
        val pendingIntent = PendingIntent.getBroadcast(this, MainActivity.ALARM_REQUEST_CODE, intent, FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
//        calendar.set(Calendar.MINUTE, 52)


//         Check we aren't setting it in the past which would trigger it to fire instantly
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, pendingIntent)

        makeText(this, "Salvo!", LENGTH_SHORT).show()
    }
}