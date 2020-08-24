package com.example.pintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException

const val PAN = "1111222233334444"

class MainViewModel : ViewModel() {

    private val _output = MutableLiveData<Output>()

    val output
        get() : LiveData<Output> = _output

    fun update(input: String) {
        try {
            _output.value = Output.Result(toPINBlock(input, PAN))
        } catch (e: Exception) {
            _output.value = Output.Error
        }
    }
}

fun toPINBlock(pin: String, pan: String) = ""
