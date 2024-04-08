package com.example.week04.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver

val listSaver = Saver<MutableList<String>, Any>(
    save = {
        it.toList()
    },
    restore = {
        mutableStateListOf<String>().apply {
            addAll(it as List<String>)
        }
    }
)