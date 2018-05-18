package com.renanrhoden.monitoracademico.main.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.databinding.ActivityMainBinding
import com.renanrhoden.monitoracademico.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainNavigation {
    override fun navigatoToAddNewDiscipline() {
        startActivity(Intent(this, this::class.java))
    }

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }
}
