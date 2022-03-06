package com.example.nestedscroll.data

import androidx.lifecycle.LiveData

class ItemRepository(private val itemsDao: ItemsDao){
    val allItems:LiveData<List<Item>> =itemsDao.getAllNotes()

    suspend fun insert(item: Item){
        itemsDao.insert(item)
    }

    suspend fun delete(item: Item){
        itemsDao.delete(item)
    }
     fun observeTotalPrice(): LiveData<Float> {
        return itemsDao.observeTotalPrice()
    }

}