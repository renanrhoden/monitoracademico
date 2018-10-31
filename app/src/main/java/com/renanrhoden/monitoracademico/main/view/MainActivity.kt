package com.renanrhoden.monitoracademico.main.view

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
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
        binding.listaRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.listaRecycler.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        val shared = getSharedPreferences(PREFERENCIA, MODE_PRIVATE)
        val all = shared.all
        val lista = all.map {
            ListagemItemDisciplinaViewModel(it.key ?: "", "", it.value.toString())
        }
        adapter.disciplinas = lista.toMutableList()
        adapter.notifyDataSetChanged()

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
                getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE).edit().remove((viewHolder as ListaAdapter.SwipeViewHolder).binding.viewModel?.nome).apply()
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
