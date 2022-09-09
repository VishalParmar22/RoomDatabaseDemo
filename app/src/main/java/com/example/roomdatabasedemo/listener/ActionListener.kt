package com.example.roomdatabasedemo.listener

interface ActionListener {
    fun onClick(position: Int, extraData : Any = "") {}
    fun onDelete(position: Int) {}
    fun onUpdate(position: Int) {}
}