package com.renanrhoden.monitoracademico.main.viewmodel

import com.renanrhoden.monitoracademico.main.view.MainNavigation

class MainViewModel {

    lateinit var navigation: MainNavigation

    fun onAddDiscipline(){
        navigation.navigatoToAddNewDiscipline()
    }
}