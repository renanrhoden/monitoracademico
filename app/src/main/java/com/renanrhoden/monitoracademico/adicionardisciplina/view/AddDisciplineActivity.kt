package com.renanrhoden.monitoracademico.adicionardisciplina.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel.AddDisciplineViewModel
import com.renanrhoden.monitoracademico.databinding.AddDisciplineActivityBinding




class AddDisciplineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<AddDisciplineActivityBinding>(this, R.layout.add_discipline_activity)
        binding.viewModel = AddDisciplineViewModel()

        val alarmManager = getSystemService(Context.ALARM_SERVICE)
    }
}