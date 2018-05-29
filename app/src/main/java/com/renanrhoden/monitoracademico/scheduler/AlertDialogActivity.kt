package com.renanrhoden.monitoracademico.scheduler

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AlertDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(this)
        val simListener = DialogInterface.OnClickListener { dialog, which -> }
        val naoListener = DialogInterface.OnClickListener { dialog, which -> }

        builder.setTitle("Foi na aula?")
                .setMessage("Foi na aula de IHC?")
                .setPositiveButton("SIM", simListener)
                .setNegativeButton("NAO", naoListener)

        builder.create().show()
    }
}