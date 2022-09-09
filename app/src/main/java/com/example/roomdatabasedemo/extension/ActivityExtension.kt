package com.example.roomdatabasedemo.extension

import com.example.roomdatabasedemo.util.Constant.NUM

fun String?.convertToLong(): Long {
    return when {
        this.isNullOrBlank() -> 0L
        this.matches(NUM) -> if (this.contains(".")) this.convertToDouble()
            .toLong() else this.toLong()
        else -> 0L
    }
}

// Double conversion
fun String?.convertToDouble(): Double {
    return when {
        this.isNullOrBlank() || this.equals("null", true) -> 0.0
        this.matches(NUM) -> this.toDouble()
        else -> 0.0
    }
}