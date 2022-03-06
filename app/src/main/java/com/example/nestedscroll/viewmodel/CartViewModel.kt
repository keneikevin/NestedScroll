package com.example.nestedscroll.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nestedscroll.data.Item
import com.example.nestedscroll.data.ItemDataBase
import com.example.nestedscroll.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application):AndroidViewModel(application) {
    val allItems:LiveData<List<Item>>
    val repository:ItemRepository

    init {
        val dao = ItemDataBase.getDatabase(application).getItemsDao()
        repository = ItemRepository(dao)
        allItems = repository.allItems

    }

    fun deleteItem(item:Item) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }


    fun addItem(item:Item) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(item)
    }
}