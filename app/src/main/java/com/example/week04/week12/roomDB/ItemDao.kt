package com.example.week04.week12.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao { //추상메소드 : 구현X but RoomLibrary가 자동으로 구현해줌
    @Query("SELECT * FROM items")
    fun getAllItems() : Flow<List<Item>> //List말고 Flow추천
    //Flow : 이미 비동기식 -> suspend 키워드 필요없음

    @Query("SELECT * FROM items where itemName=:itemName")
    fun getItems(itemName:String) : Flow<List<Item>>
    //insert, update, delete는 별도의 sql구분 필요없음 : composable에서 실행X (코루틴에서 실행)
    @Insert
    suspend fun InsertItem(item: Item)

    @Update
    suspend fun UpdateItem(item: Item)

    @Delete
    suspend fun DeleteItem(item: Item)
}