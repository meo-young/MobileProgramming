package com.example.week04.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.week04.R
import java.io.File
import java.util.Scanner

class VocDataViewModel2 : ViewModel() {
    var vocList = mutableStateListOf<VocData>()
        private set
    init{
        vocList.addAll(readWordFile())
    }
    private fun readWordFile():MutableList<VocData>{
        val scan = Scanner(File("C:\\Users\\eotn2\\MobileProgramming\\app\\src\\main\\res\\raw\\word.txt"))
        val wordList = mutableListOf<VocData>()
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            Log.d("word", word)
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