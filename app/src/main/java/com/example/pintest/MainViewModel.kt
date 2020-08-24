package com.example.pintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException

class MainViewModel : ViewModel() {

    private val _output = MutableLiveData<Output>()

    val output
        get() : LiveData<Output> = _output

    fun update(input: String) {
        try {
            val pin = input.toLong()
            _output.value = Output.Result(pin.toPINBLock())
        } catch (e: NumberFormatException) {
            _output.value = Output.Error
        }
    }
}

fun Long.toPINBLock() = toString()
