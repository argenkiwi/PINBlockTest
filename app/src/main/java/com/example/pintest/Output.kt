package com.example.pintest

sealed class Output {
    object Error : Output()
    data class Result(val result: String) : Output()
}
