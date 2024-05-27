package com.example.week04.week12.viewmodel
import com.example.week04.week12.roomDB.Item
import com.example.week04.week12.roomDB.ItemDatabase

class Repository(private val db : ItemDatabase){ //dao에서 제공하는 기능을 그대로 제공
    val dao = db.getDao()
    suspend fun InsertItem(item: Item){
        dao.InsertItem(item)
    }

    suspend fun UpdateItem(item: Item){
        dao.UpdateItem(item)
    }

    suspend fun DeleteItem(item: Item){
        dao.DeleteItem(item)
    }

    fun getAllItems() = dao.getAllItems()

    fun getItems(itemName:String) = dao.getItems(itemName) //"$itemName%
}