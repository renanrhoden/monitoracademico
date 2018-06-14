package com.renanrhoden.monitoracademico.main.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.renanrhoden.monitoracademico.PREFERENCIA
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.adicionardisciplina.view.AddDisciplineActivity
import com.renanrhoden.monitoracademico.databinding.ActivityMainBinding
import com.renanrhoden.monitoracademico.main.viewmodel.ListagemItemDisciplinaViewModel
import com.renanrhoden.monitoracademico.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainNavigation {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    private val adapter = ListaAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.navigation = this
        binding.viewModel = viewModel
        binding.listaRecycler.layoutManager = LinearLayoutManager(this)
        binding.listaRecycler.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        val shared = getSharedPreferences(PREFERENCIA, MODE_PRIVATE)
        val all = shared.all

        val lista = all.map {
            ListagemItemDisciplinaViewModel(it.key, "quarta", it.value.toString())
        }
        adapter.disciplinas = lista.toMutableList()
        adapter.notifyDataSetChanged()
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.listaRecycler.adapter as ListaAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.listaRecycler)
    }

    override fun navigatoToAddNewDiscipline() {
        startActivity(Intent(this, AddDisciplineActivity::class.java))
    }

    companion object {
        const val ALARM_REQUEST_CODE = 133
    }
}
