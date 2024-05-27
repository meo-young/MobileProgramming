package com.example.week04.week12.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "items")
data class Item (
    var itemName:String,
    var itemQuantity:Int,
    @PrimaryKey(autoGenerate = true) val itemId:Int=0
)