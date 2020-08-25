package com.example.pintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

const val PAN = "1111222233334444"

class MainViewModel : ViewModel() {

    private val _output = MutableLiveData<Output>()

    val output
        get() : LiveData<Output> = _output

    fun update(input: String) {
        viewModelScope.launch {
            try {
                _output.value = Output.Result(toPINBlock(input, PAN))
            } catch (e: Exception) {
                _output.value = Output.Error
            }
        }
    }
}

fun toIPB(pin: String): String {
    val ipb = CharArray(16);
    ipb[0] = '3' // ISO-3
    ipb[1] = Integer.toHexString(pin.length)[0]
    pin.toCharArray().copyInto(ipb, 2)
    val options = arrayOf('a', 'b', 'c', 'd', 'f')
    for (i in 2 + pin.length until ipb.size) {
        ipb[i] = options[Random.nextInt(options.size)]
    }
    return String(ipb)
}

fun toPANBlock(pan: String): String {
    val panBlock = CharArray(16)
    for (i in 0..3) {
        panBlock[i] = '0'
    }
    pan.takeLast(12).toCharArray().copyInto(panBlock, 4)
    return String(panBlock)
}

suspend fun toPINBlock(input: String, pan: String): String {
    when {
        input.toLongOrNull() == null -> throw Exception("Invalid PIN format")
        input.length < 4 -> throw Exception("Invalid PIN length")
    }

    val ipb = toIPB(input);
    val panBlock = toPANBlock(pan);
    return (ipb.toLong(16) xor panBlock.toLong(16)).toString(16)
}
