package com.example.pintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _output = MutableLiveData<String>()

    val output
        get() : LiveData<String> = _output

    fun update(input: String) {
        _output.value = input
    }
}
