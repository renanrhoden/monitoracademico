package com.renanrhoden.monitoracademico.main.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.view.AddDisciplineActivity
import com.renanrhoden.monitoracademico.databinding.ActivityMainBinding
import com.renanrhoden.monitoracademico.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainNavigation {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.navigation = this
        binding.viewModel = viewModel
    }

    override fun navigatoToAddNewDiscipline() {
        startActivity(Intent(this, AddDisciplineActivity::class.java))
    }
}
