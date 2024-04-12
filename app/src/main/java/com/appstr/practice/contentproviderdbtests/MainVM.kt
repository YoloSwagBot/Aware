package com.appstr.practice.contentproviderdbtests

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class MainVM(appli: Application) : AndroidViewModel(appli) {

    private val _dataset = mutableStateOf(listOf<String>())
    val dataset = _dataset.value




    fun onClickQueryDevice(){

    }

    fun onClickDeleteItems(){
        _dataset.value = listOf()
    }


}