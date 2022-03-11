package com.example.nestedscroll.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemTable")
data class Item(
    @ColumnInfo(name = "title")val title:String,
    @ColumnInfo(name = "price")val price:Int,
    @ColumnInfo(name = "img")val img:String

    ){
    @PrimaryKey(autoGenerate = true)
    var id=0

}
