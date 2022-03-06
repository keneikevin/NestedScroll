package com.example.nestedscroll.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemDataBase :RoomDatabase(){
    abstract fun getItemsDao():ItemsDao

    companion object{
        @Volatile
        private var INSTANCE:ItemDataBase?=null

        fun getDatabase(context: Context):ItemDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   ItemDataBase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}