package com.example.week04.week12.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false) //table이 여러개면 ,로 이어주면됨
//exportSchema : 외부에 Schema정보 주지 않을 거임 (true면 json으로 외부에 정보 제공 가능)
abstract class ItemDatabase : RoomDatabase(){
    abstract fun getDao() : ItemDao

    companion object{
        private var database : ItemDatabase? = null
        fun getDatabase(context: Context) = database ?: Room.databaseBuilder( //database가 null이면
            context, ItemDatabase::class.java, "itemdb" //db생성
        ).build().also {
            database = it //초기화
        }
    }
}