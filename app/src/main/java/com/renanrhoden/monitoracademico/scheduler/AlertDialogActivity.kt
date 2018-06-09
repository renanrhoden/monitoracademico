package com.renanrhoden.monitoracademico.scheduler

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.renanrhoden.monitoracademico.DISCIPLINE

class AlertDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(this)
        val simListener = DialogInterface.OnClickListener { dialog, which -> finish()}
        val naoListener = DialogInterface.OnClickListener { dialog, which -> finish()}

        val disciplina = intent.getStringExtra(DISCIPLINE)
        builder.setTitle("Monitor de frequência")
                .setMessage("Foi na aula de $disciplina?")
                .setPositiveButton("SIM", simListener)
                .setNegativeButton("NAO", naoListener)

        builder.create().show()
    }
}