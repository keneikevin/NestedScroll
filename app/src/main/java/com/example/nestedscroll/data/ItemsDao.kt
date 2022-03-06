package com.example.nestedscroll.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM itemTable")
    fun getAllNotes(): LiveData<List<Item>>

    @Query("SELECT SUM(price)FROM itemTable")
    fun observeTotalPrice(): LiveData<Float>
}