package com.renanrhoden.monitoracademico.main.view

import android.content.Context.MODE_PRIVATE
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.ViewGroup
import com.renanrhoden.monitoracademico.PREFERENCIA
import com.renanrhoden.monitoracademico.R
import com.renanrhoden.monitoracademico.databinding.ListagemItemDisciplinaBinding
import com.renanrhoden.monitoracademico.main.viewmodel.ListagemItemDisciplinaViewModel

class ListaAdapter : RecyclerView.Adapter<ListaAdapter.SwipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val binding = DataBindingUtil.inflate<ListagemItemDisciplinaBinding>(
                from(parent.context),
                R.layout.listagem_item_disciplina,
                parent,
                false
        )
        return SwipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.binding.viewModel = disciplinas[position]
        holder.setup()
    }

    lateinit var disciplinas: MutableList<ListagemItemDisciplinaViewModel>

    fun removeAt(position: Int) {
        disciplinas.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return disciplinas.size
    }

    class SwipeViewHolder constructor(val binding: ListagemItemDisciplinaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup() {
            val shared = binding.root.context.getSharedPreferences(PREFERENCIA, MODE_PRIVATE)
            val discipline = binding.viewModel?.nome

            binding.addOne.setOnClickListener {
                shared.edit().putInt(discipline, shared.getInt(discipline, 0) + 1).apply()
                binding.numeroFaltas.text = shared.getInt(discipline, 0).toString()
            }

            binding.minusOne.setOnClickListener {
                shared.edit().putInt(discipline, shared.getInt(discipline, 0) - 1).apply()
                binding.numeroFaltas.text = shared.getInt(discipline, 0).toString()
            }

            binding.numeroFaltas.text = shared.getInt(discipline, 0).toString()
        }
    }
}