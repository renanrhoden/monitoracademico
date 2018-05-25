package com.renanrhoden.monitoracademico.adicionardisciplina.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class AddDisciplineViewModel {
    val mNameDiscipline = ObservableField<String>("")
    val mSegundaChecked = ObservableBoolean(false)
    val mTercaChecked = ObservableBoolean(false)
    val mQuartaChecked = ObservableBoolean(false)
    val mQuintaChecked = ObservableBoolean(false)
    val mSextaChecked = ObservableBoolean(false)
    val mSabadoChecked = ObservableBoolean(false)
    val mDomingoChecked = ObservableBoolean(false)
}