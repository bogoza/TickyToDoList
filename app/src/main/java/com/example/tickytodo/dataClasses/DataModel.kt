package com.example.tickytodo.dataClasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel:ViewModel() {
    val openEmptyFragment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val openAddTaskFragment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}