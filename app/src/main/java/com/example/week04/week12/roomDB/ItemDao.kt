package com.example.week04.week12.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems() : Flow<List<Item>>


    @Query("SELECT * FROM items where itemName LIKE '%' || :itemName || '%'")
    fun getItems(itemName:String) : Flow<List<Item>>

    @Insert
    suspend fun InsertItem(item: Item)

    @Update
    suspend fun UpdateItem(item: Item)

    @Delete
    suspend fun DeleteItem(item: Item)
}