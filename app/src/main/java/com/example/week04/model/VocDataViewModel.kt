package com.example.week04.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.week04.R
import java.util.Scanner

class VocDataViewModel(private val application: Application) : AndroidViewModel(application) {

    var vocList = mutableStateListOf<VocData>()
        private set
    init{
        vocList.addAll(readWordFile())
    }

    private fun readWordFile():MutableList<VocData>{
        val context = application.applicationContext
        val scan = Scanner(context.resources.openRawResource(R.raw.word))
        val wordList = mutableListOf<VocData>()
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            wordList.add(VocData(word, meaning))
        }
        scan.close()
        return wordList
    }

    fun changeOpenStatus(index:Int){
        vocList[index] = vocList[index].copy(isOpen = !vocList[index].isOpen)
    }
}